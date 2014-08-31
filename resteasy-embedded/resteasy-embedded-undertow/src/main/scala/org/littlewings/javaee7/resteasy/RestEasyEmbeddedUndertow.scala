package org.littlewings.javaee7.resteasy

import scala.io.StdIn
import scala.collection.JavaConverters._

import java.util.Date

import javax.ws.rs.{ApplicationPath, DefaultValue, GET, Path, Produces, QueryParam}
import javax.ws.rs.core.{Application, MediaType}

import io.undertow.servlet.api.DeploymentInfo
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer

@ApplicationPath("rest")
class JaxrsApplication extends Application {
  override def getClasses: java.util.Set[Class[_]] =
    Set(classOf[HelloResource], classOf[ScalaResource])
      .asJava
      .asInstanceOf[java.util.Set[Class[_]]]
}

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

object RestEasyEmbeddedUndertow {
  def main(args: Array[String]): Unit = {
    val server = new UndertowJaxrsServer

    // この場合のアクセスURLは、http://localhost:8081/di/rest/～（リソースで指定したパス）
    val deployment = server.undertowDeployment(classOf[JaxrsApplication])
    deployment.setContextPath("/di")
    deployment.setDeploymentName("DI")
    server.deploy(deployment)

    // こちらでも可
    // この場合のアクセスURLは、http://localhost:8081/rest/～（リソースで指定したパス）
    //server.deploy(classOf[JaxrsApplication])

    // こちらでも可
    // この場合のアクセスURLは、http://localhost:8081/root/～（リソースで指定したパス）
    //server.deploy(classOf[JaxrsApplication], "/root")

    server.start()  // デフォルトポート8081で起動

    // server.start {
    //   io.undertow.Undertow
    //     .builder
    //     .addHttpListener(8080, "localhost")  // Listenポート、アドレスを指定して起動
    // }

    println(s"[${new Date}] RestEasyUndertowHttpd startup.")

    StdIn.readLine()

    // 終了処理
    server.stop()

    println(s"[${new Date}] RestEasyUndertowHttpd shutdown.")
  }
}
