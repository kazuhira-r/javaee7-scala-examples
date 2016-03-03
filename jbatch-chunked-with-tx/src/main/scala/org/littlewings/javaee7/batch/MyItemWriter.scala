package org.littlewings.javaee7.batch

import java.io.Serializable
import java.util
import javax.batch.api.chunk.ItemWriter
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named("MyItemWriter")
class MyItemWriter extends ItemWriter {
  private val logger: Logger = Logger.getLogger(getClass)

  override def open(checkpoint: Serializable): Unit =
    logger.infof("open.")

  override def writeItems(items: util.List[AnyRef]): Unit =
    logger.infof(s"writeItems[${items.size()}].")

  override def checkpointInfo(): Serializable = {
    logger.infof("checkpoint.")
    null
  }

  override def close(): Unit =
    logger.infof("close.")
}
