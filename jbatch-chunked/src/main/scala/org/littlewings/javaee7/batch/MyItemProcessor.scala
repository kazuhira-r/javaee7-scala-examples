package org.littlewings.javaee7.batch

import javax.batch.api.chunk.ItemProcessor
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ja.JapaneseAnalyzer
import org.apache.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.jboss.logging.Logger

import scala.collection.JavaConverters._

@Dependent
@Named("MyItemProcessor")
class MyItemProcessor extends ItemProcessor {
  private val logger: Logger = Logger.getLogger(getClass)

  private val analyzer: Analyzer = new JapaneseAnalyzer

  override def processItem(item: scala.Any): AnyRef = {
    logger.infof("process item.")

    val tokenStream = analyzer.tokenStream("", item.asInstanceOf[String])
    val charTermAttr = tokenStream.addAttribute(classOf[CharTermAttribute])
    val partOfSpeechAttr = tokenStream.addAttribute(classOf[PartOfSpeechAttribute])

    tokenStream.reset()

    try {
      Iterator
        .continually(tokenStream.incrementToken())
        .takeWhile(identity)
        .filter(_ => partOfSpeechAttr.getPartOfSpeech.contains("名詞"))
        .map(_ => charTermAttr.toString)
        .toList
    } finally {
      tokenStream.end()
      tokenStream.close()
    }
  }
}
