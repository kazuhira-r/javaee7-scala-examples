package org.littlewings.javaee7.batch

import javax.batch.api.BatchProperty
import javax.batch.api.chunk.AbstractItemReader
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Dependent
@Named
class MyItemReader extends AbstractItemReader {
  val logger: Logger = Logger.getLogger(getClass)

  var languages: Iterator[String] = _

  @Inject
  @BatchProperty
  var start: Int = _

  @Inject
  @BatchProperty
  var step: Int = _

  override def open(checkpoint: java.io.Serializable): Unit = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#open")

    languages =
      List("Java", "Scala", "Groovy", "Clojure", "Kotlin", "Perl", "Ruby", "Python", "PHP", "C")
        .drop(start)
        .take(step)
        .iterator
  }

  override def readItem(): AnyRef =
    if (languages.hasNext) languages.next
    else null
}
