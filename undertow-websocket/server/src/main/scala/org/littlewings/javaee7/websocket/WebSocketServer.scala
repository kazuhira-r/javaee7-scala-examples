package org.littlewings.javaee7.websocket

import java.time.LocalDateTime

import io.undertow.servlet.Servlets
import io.undertow.servlet.api.DeploymentInfo
import io.undertow.websockets.jsr.WebSocketDeploymentInfo
import io.undertow.{Handlers, Undertow}

import scala.io.StdIn

object WebSocketServer {
  def main(args: Array[String]): Unit = {
    val webSocketDeploymentInfo =
      new WebSocketDeploymentInfo()
        .addEndpoint(classOf[EchoServer])

    val builder =
      new DeploymentInfo()
        .setClassLoader(getClass.getClassLoader)
        .setContextPath("/")
        .setDeploymentName("myapp.war")
        .addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, webSocketDeploymentInfo)

    val manager = Servlets.defaultContainer.addDeployment(builder)
    manager.deploy()

    val path =
      Handlers
        .path
        .addPrefixPath("/", manager.start())

    val undertow =
      Undertow
        .builder
        .addHttpListener(8080, "localhost")
        .setHandler(path)
        .build()

    undertow.start()

    StdIn.readLine(s"[${LocalDateTime.now}] Please Enter, Stop.")

    undertow.stop()
  }
}
