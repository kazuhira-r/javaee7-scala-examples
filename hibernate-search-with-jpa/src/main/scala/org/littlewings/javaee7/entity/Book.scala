package org.littlewings.javaee7.entity

import scala.beans.BeanProperty

import javax.persistence.{Column, Entity, Id, Table, Version}

import org.apache.lucene.analysis.ja.JapaneseAnalyzer
import org.hibernate.search.annotations.{Analyze, Analyzer, Field, Index, Indexed}

@SerialVersionUID(1L)
@Entity
@Table(name = "book")
@Indexed
class Book extends Serializable {
  @Id
  @Column
  @Field(analyze = Analyze.NO)
  @BeanProperty
  var isbn: String = _

  @Column
  @Field
  @Analyzer(impl = classOf[JapaneseAnalyzer])
  @BeanProperty
  var title: String = _

  @Column
  @Field(analyze = Analyze.NO)
  @BeanProperty
  var price: Int = _

  @Column
  @Field
  @Analyzer(impl = classOf[JapaneseAnalyzer])
  @BeanProperty
  var summary: String = _

  @Column(name = "version_no")
  @BeanProperty
  @Version
  var versionNo: Int = _

  override def toString: String =
    s"isbn = $isbn, title = $title, price = $price, summary = $summary, versionNo = $versionNo"
}
