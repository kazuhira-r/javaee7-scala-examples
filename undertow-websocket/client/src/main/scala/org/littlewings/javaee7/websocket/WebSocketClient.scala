package org.littlewings.javaee7.websocket

import java.net.URI
import javax.websocket.{CloseReason, ContainerProvider}

import scala.io.StdIn

object WebSocketClient {
  def main(args: Array[String]): Unit = {
    val container = ContainerProvider.getWebSocketContainer
    val session = container.connectToServer(classOf[EchoClient], URI.create("ws://localhost:8080/echo"))

    Iterator
      .continually(StdIn.readLine("enter text> "))
      .takeWhile(_.trim != "exit")
      .filter(!_.trim.isEmpty)
      .foreach { message => session.getBasicRemote.sendText(message) }

    session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "byebye!!"))
  }
}
