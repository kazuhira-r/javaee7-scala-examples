package org.littlewings.javaee7.rest

import java.lang.annotation.Annotation
import java.lang.reflect.Type
import java.io._
import java.nio.charset.StandardCharsets

import javax.ws.rs.Produces
import javax.ws.rs.core.{MediaType, MultivaluedMap}
import javax.ws.rs.ext.{MessageBodyWriter, Provider}

import com.gilt.handlebars.scala.binding.dynamic._
import com.gilt.handlebars.scala.Handlebars

@Provider
@Produces(Array(MediaType.TEXT_HTML))
class HandlebarsMessageBodyWriter extends MessageBodyWriter[HandlebarsView] {
  override def getSize(view: HandlebarsView,
                       tpe: Class[_],
                       genericType: Type,
                       annotations: Array[Annotation],
                       mediaType: MediaType): Long =
    -1

  override def isWriteable(tpe: Class[_],
                           genericType: Type,
                           annotations: Array[Annotation],
                           mediaType: MediaType): Boolean =
    tpe.isAssignableFrom(classOf[HandlebarsView])

  override def writeTo(view: HandlebarsView,
                       tpe: Class[_],
                       genericType: Type,
                       annotations: Array[Annotation],
                       mediaType: MediaType,
                       httpHeaders: MultivaluedMap[String, AnyRef],
                       entityStream: OutputStream): Unit = {
    val writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8)

    val cl = Thread.currentThread.getContextClassLoader
    
    val is = cl.getResourceAsStream(view.templatePath)
    val templateAsString =
      try {
        val reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        try {
          Iterator
            .continually(reader.read())
            .takeWhile(_ != -1)
            .map(_.asInstanceOf[Char])
            .mkString
        } finally {
          reader.close()
        }
      } finally {
        is.close()
      }

    val template = Handlebars(templateAsString)
    writer.write(template(view.binding))
    writer.flush()
  }
}
