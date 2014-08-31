package org.littlewings.javaee7.resteasy

import scala.io.StdIn

import java.net.InetSocketAddress
import java.util.Date

import javax.ws.rs.{DefaultValue, GET, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType

import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder

import com.sun.net.httpserver.{HttpHandler, HttpServer}

@Path("hello")
class HelloResource {
  @GET
  @Path("index")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def index: String =
    "Hello World"
}

@Path("scala")
class ScalaResource {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def index(@QueryParam("p") @DefaultValue("World") p: String): String =
    s"Hello $p by Scala"
}

object RestEasyEmbeddedJdkHttpServer {
  def main(args: Array[String]): Unit = {
    val server = HttpServer.create(new InetSocketAddress(8080), 10)

    val contextBuilder = new HttpContextBuilder

    Seq(classOf[HelloResource], classOf[ScalaResource])
      .foreach(contextBuilder.getDeployment.getActualResourceClasses.add)

    val context = contextBuilder.bind(server)

    server.start()

    println(s"[${new Date}] RestEasyJdkHttpd startup[${server.getAddress}].")

    StdIn.readLine()

    // 終了処理
    contextBuilder.cleanup()
    server.stop(0)

    println(s"[${new Date}] RestEasyJdkHttpd shutdown.")
  }
}
