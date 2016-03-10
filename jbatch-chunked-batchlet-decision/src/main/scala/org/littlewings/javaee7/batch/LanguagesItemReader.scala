package org.littlewings.javaee7.batch

import javax.batch.api.chunk.AbstractItemReader
import javax.enterprise.context.Dependent
import javax.inject.Named

@Dependent
@Named
class LanguagesItemReader extends AbstractItemReader {
  private val languages: Iterator[String] =
    List("Java", "Scala", "Groovy", "Clojure", "Kotlin", "Perl", "Ruby", "Python", "PHP", "C").iterator

  override def readItem(): AnyRef =
    if (languages.hasNext) languages.next
    else null
}
