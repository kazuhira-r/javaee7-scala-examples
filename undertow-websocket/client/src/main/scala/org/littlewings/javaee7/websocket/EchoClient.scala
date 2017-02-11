package org.littlewings.javaee7.websocket

import javax.websocket._

import org.jboss.logging.Logger

@ClientEndpoint
class EchoClient {
  val logger: Logger = Logger.getLogger(classOf[EchoClient])

  @OnMessage
  def onMessage(session: Session, msg: String): Unit = {
    logger.infof("received from server: %s", msg)
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
