package org.littlewings.javaee7.batch

import javax.batch.api.chunk.ItemProcessor
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named("MyItemProcessor")
class MyItemProcessor extends ItemProcessor {
  private val logger: Logger = Logger.getLogger(getClass)

  override def processItem(item: AnyRef): AnyRef = {
    logger.infof("processItem.")
    item
  }
}
