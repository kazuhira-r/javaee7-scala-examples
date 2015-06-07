package org.littlewings.javaee7.batch

import javax.batch.api.{BatchProperty, AbstractBatchlet}
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger
import org.littlewings.javaee7.service.LanguageService

@Named
@Dependent
class MyBatchlet extends AbstractBatchlet {
  @Inject
  private var jobContext: JobContext = _

  @transient
  private val logger: Logger = Logger.getLogger(getClass)

  @Inject
  @BatchProperty
  private var message: String = _

  @Inject
  @BatchProperty
  private var id: String = _

  @Inject
  private var languageService: LanguageService = _

  @throws(classOf[Exception])
  override def process(): String = {
    logger.infof("***** start process MyBatchlet job. *****")
    logger.infof("job name = %s", jobContext.getJobName)

    val properties = jobContext.getProperties

    logger.infof("properties jobProperty = %s", properties.getProperty("jobProperty"))
    logger.infof("batch property message = %s", message)

    val language = languageService.findById(id.toLong)

    logger.infof("found entity, %s", language)
    logger.infof("***** end process MyBatchlet job. *****")

    "done."
  }
}
