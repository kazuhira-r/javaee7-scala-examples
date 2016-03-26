package org.littlewings.javaee7.batch

import java.util.concurrent.TimeUnit
import javax.batch.api.AbstractBatchlet
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TraceBatchlet extends AbstractBatchlet {
  val logger: Logger = Logger.getLogger(getClass)

  override def process(): String = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#process")

    TimeUnit.SECONDS.sleep(3L)

    "PROCESS"
  }
}
