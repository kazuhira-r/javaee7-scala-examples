package org.littlewings.javaee7.batch

import java.util.concurrent.TimeUnit
import javax.batch.api.{AbstractBatchlet, BatchProperty}
import javax.batch.runtime.context.StepContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Dependent
@Named
class MyBatchlet extends AbstractBatchlet {
  val logger: Logger = Logger.getLogger(getClass)

  @Inject
  @BatchProperty
  var batchName: String = _

  @Inject
  @BatchProperty
  var thrownException: Boolean = _

  @Inject
  var stepContext: StepContext = _

  override def process(): String = {
    logger.infof("[%s] batchName = %s, stepName = %s start.", Array(Thread.currentThread.getName, batchName, stepContext.getStepName): _*)

    TimeUnit.SECONDS.sleep(3L)

    if (thrownException) {
      throw new RuntimeException("Oops!!")
    }

    logger.infof("[%s] batchName = %s, stepName = %s end.", Array(Thread.currentThread.getName, batchName, stepContext.getStepName): _*)

    "STEP-FINISH"
  }
}
