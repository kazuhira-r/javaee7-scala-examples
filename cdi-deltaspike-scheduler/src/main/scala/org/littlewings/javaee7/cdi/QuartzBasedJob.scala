package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.scheduler.api.Scheduled
import org.quartz.{Job, JobExecutionContext}
import org.slf4j.{Logger, LoggerFactory}

@Scheduled(cronExpression = "0 0/1 * * * ?")
@ApplicationScoped
class QuartzBasedJob extends Job {
  @Inject
  var applicationScopedMessageService: ApplicationScopedMessageService = _

  @Inject
  var sessionScopedMessageService: SessionScopedMessageService = _

  @Inject
  var requestScopedMessageService: RequestScopedMessageService = _

  @Inject
  var pseudoScopedMessageService: PseudoScopedMessageService = _

  val logger: Logger = LoggerFactory.getLogger(getClass)

  override def execute(context: JobExecutionContext): Unit = {
    logger.info("[{}] startup job", getClass.getSimpleName)

    applicationScopedMessageService.loggingMessage()
    sessionScopedMessageService.loggingMessage()
    requestScopedMessageService.loggingMessage()
    pseudoScopedMessageService.loggingMessage()

    logger.info("[{}] end job", getClass.getSimpleName)
  }
}

@Scheduled(cronExpression = "10 0/1 * * * ?")
@ApplicationScoped
class QuartzBasedJob2 extends Job {
  @Inject
  var applicationScopedMessageService: ApplicationScopedMessageService = _

  @Inject
  var sessionScopedMessageService: SessionScopedMessageService = _

  @Inject
  var requestScopedMessageService: RequestScopedMessageService = _

  @Inject
  var pseudoScopedMessageService: PseudoScopedMessageService = _

  val logger: Logger = LoggerFactory.getLogger(getClass)

  override def execute(context: JobExecutionContext): Unit = {
    logger.info("[{}] startup job", getClass.getSimpleName)

    applicationScopedMessageService.loggingMessage()
    sessionScopedMessageService.loggingMessage()
    requestScopedMessageService.loggingMessage()
    pseudoScopedMessageService.loggingMessage()

    logger.info("[{}] end job", getClass.getSimpleName)
  }
}
