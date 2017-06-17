package org.littlewings.javaee7.batch

import javax.batch.api.{AbstractBatchlet, BatchProperty}
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Named
@Dependent
class MyBatchlet extends AbstractBatchlet {
  private[this] val logger: Logger = Logger.getLogger(getClass)

  @Inject
  @BatchProperty
  private[this] var batchletFail: Boolean = _

  override def process(): String = {
    logger.info("process batchlet")

    if (batchletFail) {
      throw new RuntimeException("Oops!! Batchlet")
    }

    "Batchlet Success"
  }
}
