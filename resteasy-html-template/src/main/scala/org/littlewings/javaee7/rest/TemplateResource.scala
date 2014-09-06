package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.core.{Context, MediaType, UriBuilder, UriInfo}

import org.jboss.resteasy.plugins.providers.html.{Renderable, Redirect, View}

@Path("template")
class TemplateResource {
  @GET
  @Path("simple")
  @Produces(Array(MediaType.TEXT_HTML))
  def simple: Renderable =
    new View("/WEB-INF/velocity/simple.vm",
             "Hello Velocity!!")

  @GET
  @Path("complex")
  @Produces(Array(MediaType.TEXT_HTML))
  def complex: Renderable =
    new View("/WEB-INF/velocity/complex.vm",
             Map("message" -> "Hello Velocity",
                 "languages" -> List("Java", "Scala", "Groovy", "Clojure").asJava).asJava,
             "it")

  @GET
  @Path("case-class")
  @Produces(Array(MediaType.TEXT_HTML))
  def caseClass: Renderable =
    new View("/WEB-INF/velocity/case-class.vm",
             List(Book("978-4844330844",
                       "Scalaスケーラブルプログラミング第2版",
                       4968),
                  Book("978-4873114675",
                       "JavaによるRESTfulシステム構築",
                       3456)).asJava,
             "books")

  @GET
  @Path("redirect")
  @Produces(Array(MediaType.TEXT_HTML))
  def redirect(@Context uriInfo: UriInfo): Renderable =
    new Redirect(uriInfo
                  .getBaseUriBuilder
                  .path(classOf[TemplateResource])
                  .path(classOf[TemplateResource],
                        "caseClass")
                          .build())

  @GET
  @Path("handlebars")
  @Produces(Array(MediaType.TEXT_HTML))
  def handlebars: Renderable =
    new View("/WEB-INF/handlebars/case-class.handlebars",
             List(Book("978-4844330844",
                       "Scalaスケーラブルプログラミング第2版",
                       4968),
                  Book("978-4873114675",
                       "JavaによるRESTfulシステム構築",
                       3456)),
             "books")
}
