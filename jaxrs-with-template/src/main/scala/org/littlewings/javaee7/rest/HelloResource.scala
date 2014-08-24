package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.ws.rs.{GET, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType

@Path("hello")
class HelloResource {
  @GET
  @Path("handlebars")
  @Produces(Array(MediaType.TEXT_HTML))
  def handlebars(@QueryParam("param") param: String): View =
    HandlebarsView("template/hello.handlebars", Map("name" -> "Kazuhira",
                                                    "param" -> Option(param).getOrElse("empty"),
                                                    "languages" -> Seq("Java", "Scala", "Groovy", "Clojure")))

  @GET
  @Path("velocity")
  @Produces(Array(MediaType.TEXT_HTML))
  def velocity(@QueryParam("param") param: String): View =
    VelocityView("template/hello.vm", Map("name" -> "Kazuhira",
                                          "param" -> Option(param).getOrElse("empty"),
                                          "languages" -> Seq("Java", "Scala", "Groovy", "Clojure").asJava))

  @GET
  @Path("plain")
  @Produces(Array(MediaType.TEXT_HTML))
  def plain(@QueryParam("param") param: String): String =
    <html>
      <body>
        <h1>XML Literal Template</h1>
        <p>{"name=Kazuhira"}</p>
        <p>{"param=" + Option(param).getOrElse("empty")}</p>
        <div>
          <p>Languages</p>
          <ul>
            {Seq("Java", "Scala", "Groovy", "Clojure").map(l => <li>{l}</li>)}
          </ul>
        </div>
      </body>
    </html>.toString
}
