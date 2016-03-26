package org.littlewings.javaee7.batch

import java.io.Serializable
import java.util.concurrent.TimeUnit
import javax.batch.api.BatchProperty
import javax.batch.api.chunk.ItemReader
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Dependent
@Named
class TraceItemReader extends ItemReader {
  val logger: Logger = Logger.getLogger(getClass)

  var languages: Iterator[String] = _

  @Inject
  @BatchProperty
  var start: Int = _

  @Inject
  @BatchProperty
  var step: Int = _

  override def open(checkpoint: Serializable): Unit = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#open")

    languages =
      List("Java", "Scala", "Groovy", "Clojure", "Kotlin", "Perl", "Ruby", "Python", "PHP", "C")
        .drop(start)
        .take(step)
        .iterator

    TimeUnit.SECONDS.sleep(3L)
  }

  override def readItem(): AnyRef = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#readItem")

    if (languages.hasNext) languages.next
    else null
  }

  override def checkpointInfo(): Serializable = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#checkpointInfo")
    null
  }

  override def close(): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#close")

}
