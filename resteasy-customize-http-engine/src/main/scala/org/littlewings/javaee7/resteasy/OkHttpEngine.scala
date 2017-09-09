package org.littlewings.javaee7.resteasy

import java.io.{ByteArrayOutputStream, InputStream}
import javax.net.ssl.{HostnameVerifier, SSLContext}
import javax.ws.rs.core.MultivaluedMap

import okhttp3.{MediaType, OkHttpClient, Request, RequestBody}
import org.jboss.resteasy.client.jaxrs.ClientHttpEngine
import org.jboss.resteasy.client.jaxrs.internal.{ClientInvocation, ClientResponse}
import org.jboss.resteasy.util.CaseInsensitiveMap

import scala.beans.BeanProperty

class OkHttpEngine extends ClientHttpEngine {
  val okHttpClient: OkHttpClient = new OkHttpClient

  @BeanProperty
  var sslContext: SSLContext = _

  @BeanProperty
  var hostnameVerifier: HostnameVerifier = _

  override def invoke(request: ClientInvocation): ClientResponse = {
    val okHttpRequestBuilder =
      new Request.Builder()
        .url(request.getUri.toURL)

    val body = Option(request.getEntity).map { _ =>
        val baos = new ByteArrayOutputStream
        request.getDelegatingOutputStream.setDelegate(baos)
        request.writeRequestBody(baos)
        baos.close()
        baos.toByteArray
    }

    val headers = request.getHeaders.asMap
    headers.entrySet.forEach(header => header.getValue.forEach(okHttpRequestBuilder.addHeader(header.getKey, _)))

    okHttpRequestBuilder.method(
      request.getMethod,
      body.map(RequestBody.create(MediaType.parse(request.getHeaders.getMediaType.toString), _)).getOrElse(null)
    )

    val okHttpRequest = okHttpRequestBuilder.build

    val okHttpResponse = okHttpClient.newCall(okHttpRequest).execute()

    val response = new ClientResponse(request.getClientConfiguration) {
      var inputStream: InputStream = _

      override def releaseConnection(): Unit = okHttpResponse.close()

      override def setInputStream(is: InputStream): Unit = inputStream = is

      override def getInputStream = {
        if (inputStream == null) {
          inputStream = okHttpResponse.body().byteStream()
        }

        inputStream
      }
    }

    response.setStatus(okHttpResponse.code())

    val responseHeaders: MultivaluedMap[String, String] = new CaseInsensitiveMap
    val headerNames = okHttpResponse.headers.names
    headerNames.forEach(name => okHttpResponse.headers(name).forEach(value => responseHeaders.add(name, value)))

    response.setHeaders(responseHeaders)
    response
  }

  override def close(): Unit = ()
}
