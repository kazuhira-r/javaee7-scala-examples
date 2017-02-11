package org.littlewings.javaee7.websocket

import javax.websocket._
import javax.websocket.server.ServerEndpoint

import org.jboss.logging.Logger

@ServerEndpoint("/echo")
class EchoServer {
  val logger: Logger = Logger.getLogger(getClass)

  @OnMessage
  def onMessage(session: Session, msg: String): Unit = {
    logger.infof("receive message = %s", msg)
    session.getBasicRemote.sendText(s"[$msg]")
  }

  @OnOpen
  def onOpen(session: Session, config: EndpointConfig): Unit =
    logger.infof("session open")

  @OnClose
  def onClose(session: Session, reason: CloseReason): Unit =
    logger.infof("close, reason = %s", reason.getReasonPhrase)

  @OnError
  def onError(session: Session, cause: Throwable): Unit =
    logger.errorf(cause, "error")
}
