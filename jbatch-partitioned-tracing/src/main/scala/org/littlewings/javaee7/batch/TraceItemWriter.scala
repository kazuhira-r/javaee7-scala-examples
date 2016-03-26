package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.chunk.ItemWriter
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TraceItemWriter extends ItemWriter {
  val logger: Logger = Logger.getLogger(getClass)

  override def writeItems(items: java.util.List[AnyRef]): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#writeItems, items = ${items}")

  override def checkpointInfo(): Serializable = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#checkpointInfo")
    null
  }

  override def close(): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#close")

  override def open(checkpoint: Serializable): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#open")
}
