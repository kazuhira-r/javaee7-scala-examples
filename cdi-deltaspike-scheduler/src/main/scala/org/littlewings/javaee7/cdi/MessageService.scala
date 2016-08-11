package org.littlewings.javaee7.cdi

import java.time.LocalDateTime
import javax.enterprise.context.{ApplicationScoped, Dependent, RequestScoped, SessionScoped}

import org.slf4j.{Logger, LoggerFactory}

trait MessageServiceSupport {
  val logger: Logger = LoggerFactory.getLogger(getClass)

  def loggingMessage(): Unit = {
    logger.info(s"Hello ${getClass.getSimpleName}@${hashCode}, now = ${LocalDateTime.now}")
  }
}

@ApplicationScoped
class ApplicationScopedMessageService extends MessageServiceSupport

@SessionScoped
@SerialVersionUID(1L)
class SessionScopedMessageService extends MessageServiceSupport with Serializable

@RequestScoped
class RequestScopedMessageService extends MessageServiceSupport

@Dependent
class PseudoScopedMessageService extends MessageServiceSupport
