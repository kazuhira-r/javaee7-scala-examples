package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.scheduler.api.Scheduled
import org.quartz.{Job, JobExecutionContext}
import org.slf4j.{Logger, LoggerFactory}

@Scheduled(cronExpression = "30 0/1 * * * ?", onStartup = false)
@ApplicationScoped
class ManualQuartzBasedJob extends Job {
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
