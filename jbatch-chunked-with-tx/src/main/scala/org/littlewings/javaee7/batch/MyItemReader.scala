package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.chunk.ItemReader
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named("MyItemReader")
class MyItemReader extends ItemReader {
  private val logger: Logger = Logger.getLogger(getClass)

  private val languages: Iterator[String] =
    List("Java", "Scala", "Groovy", "Clojure", "Kotlin", "Perl", "Ruby", "Python", "PHP", "C").iterator

  override def open(checkpoint: Serializable): Unit =
    logger.infof("open.")

  override def readItem(): AnyRef = {
    logger.infof("readItem.")

    if (languages.hasNext) languages.next
    else null
  }

  override def checkpointInfo(): Serializable = {
    logger.infof("checkpoint.")
    null
  }

  override def close(): Unit =
    logger.infof("close.")
}
