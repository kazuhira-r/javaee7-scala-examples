package org.littlewings.javaee7.servlet

import scala.collection.JavaConverters._

import java.io.{BufferedReader, InputStreamReader, IOException}
import java.nio.charset.StandardCharsets

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.gilt.handlebars.scala.binding.dynamic._
import com.gilt.handlebars.scala.Handlebars

import resource._

@WebServlet(Array("*.handlebars"))
class HandlebarsScalaServlet extends HttpServlet {
  private var prefix: String = _

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override protected def doGet(req: HttpServletRequest, res: HttpServletResponse): Unit =
    process(req, res)

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override protected def doPost(req: HttpServletRequest, res: HttpServletResponse): Unit =
    process(req, res)

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  protected def process(req: HttpServletRequest, res: HttpServletResponse): Unit = {
    res.setCharacterEncoding(StandardCharsets.UTF_8.toString)

    val writer = res.getWriter

    val context = req.getServletContext
    val path =
      if (req.getPathInfo != null)
          req.getServletPath + req.getPathInfo
        else
          req.getServletPath

    val contents =
      for {
        is <- managed(context.getResourceAsStream(path))
        isr <- managed(new InputStreamReader(is, StandardCharsets.UTF_8))
        reader <- managed(new BufferedReader(isr))
      } yield {
        Iterator
          .continually(reader.read())
          .takeWhile(_ != -1)
          .map(_.asInstanceOf[Char])
          .mkString
      }

    val template = Handlebars(contents.acquireAndGet(identity))

    val bindings =
      req.getAttributeNames.asScala.foldLeft(Map.empty[String, Any]) { (acc, n) =>
        acc + (n -> req.getAttribute(n))
      }

    writer.write(template(bindings))
    writer.flush()
  }
}
