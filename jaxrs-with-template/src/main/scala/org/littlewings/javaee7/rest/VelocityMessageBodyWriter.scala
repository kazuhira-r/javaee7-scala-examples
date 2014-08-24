package org.littlewings.javaee7.rest

import java.lang.annotation.Annotation
import java.lang.reflect.Type
import java.io._
import java.nio.charset.StandardCharsets

import javax.ws.rs.Produces
import javax.ws.rs.core.{MediaType, MultivaluedMap}
import javax.ws.rs.ext.{MessageBodyWriter, Provider}

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader

@Provider
@Produces(Array(MediaType.TEXT_HTML))
class VelocityMessageBodyWriter extends MessageBodyWriter[VelocityView] {
  val engine: VelocityEngine = {
    val ve = new VelocityEngine
    ve.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
    ve.addProperty("classpath.resource.loader.class", classOf[ClasspathResourceLoader].getName)
    ve
  }

  override def getSize(view: VelocityView,
                       tpe: Class[_],
                       genericType: Type,
                       annotations: Array[Annotation],
                       mediaType: MediaType): Long =
    -1

  override def isWriteable(tpe: Class[_],
                           genericType: Type,
                           annotations: Array[Annotation],
                           mediaType: MediaType): Boolean =
    tpe.isAssignableFrom(classOf[VelocityView])

  override def writeTo(view: VelocityView,
                       tpe: Class[_],
                       genericType: Type,
                       annotations: Array[Annotation],
                       mediaType: MediaType,
                       httpHeaders: MultivaluedMap[String, AnyRef],
                       entityStream: OutputStream): Unit = {
    val writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8)

    val template =
      engine.getTemplate(view.templatePath, StandardCharsets.UTF_8.toString)
    val context = new VelocityContext

    for ((k, v) <- view.binding) {
      context.put(k, v)
    }

    template.merge(context, writer)
    writer.flush()
  }
}
