package org.littlewings.javaee7.batch

import javax.batch.api.chunk.AbstractItemWriter
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Named
@Dependent
class MyItemWriter extends AbstractItemWriter {
  private[this] val logger: Logger = Logger.getLogger(getClass)
  private[this] var counter: Int = _

  override def open(checkpoint: java.io.Serializable): Unit =
    logger.info("item writer start")

  override def writeItems(items: java.util.List[AnyRef]): Unit = {
    logger.infof("write items, size = %d", items.size)

    counter += items.size()
  }

  override def close(): Unit = {
    logger.infof("total count = %d", counter)
    logger.info("item writer end")
  }
}
