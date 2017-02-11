package org.littlewings.javaee7.rest

import java.time.LocalDateTime
import javax.servlet.DispatcherType

import io.undertow.Undertow
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.FilterInfo
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer

import scala.io.StdIn

object Server {
  def main(args: Array[String]): Unit = {
    val server = new UndertowJaxrsServer
    val deployment = server.undertowDeployment(classOf[JaxrsActivator])
    deployment.setContextPath("")
    deployment.setDeploymentName("resteasy-swagger")
    deployment.addFilter(Servlets.filter("corsFilter", classOf[CorsFilter]))
    deployment.addFilterUrlMapping("corsFilter", "/*", DispatcherType.REQUEST)
    server.deploy(deployment)

    server.start(Undertow
            .builder
            .addHttpListener(8080, "localhost"))

    StdIn.readLine(s"[${LocalDateTime.now}] Server startup. enter, stop.")

    server.stop()
  }
}
