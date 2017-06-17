package org.littlewings.javaee7.batch

import javax.batch.api.BatchProperty
import javax.batch.api.chunk.AbstractItemReader
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Named
@Dependent
class MyItemReader extends AbstractItemReader {
  private[this] val logger: Logger = Logger.getLogger(getClass)

  private[this] var items: Iterator[Int] = _

  private[this] var checkpoint: MyCheckPoint = _

  @Inject
  @BatchProperty
  private[this] var readerFail: Boolean = _

  @Inject
  @BatchProperty
  private[this] var readerFailCount: Int = _

  override def open(checkpoint: java.io.Serializable): Unit = {
    checkpoint match {
      case null =>
        this.checkpoint = new MyCheckPoint
      case cp: MyCheckPoint =>
        this.checkpoint = cp
    }
    logger.infof("item reader start, from %d", this.checkpoint.counter)

    items = (this.checkpoint.counter until 100).iterator
  }

  override def readItem(): AnyRef = {
    if (readerFail && checkpoint.counter == readerFailCount) {
      throw new RuntimeException(s"Oops!! ItemReader, count = ${checkpoint.counter}")
    }

    checkpoint.counter += 1

    if (checkpoint.counter % 25 == 0) {
      logger.infof("read count = %d", checkpoint.counter)
    }

    if (items.hasNext) {
      Integer.valueOf(items.next())
    } else {
      null
    }
  }

  override def close(): Unit =
    logger.info("item reader end")

  override def checkpointInfo(): java.io.Serializable =
    checkpoint
}
