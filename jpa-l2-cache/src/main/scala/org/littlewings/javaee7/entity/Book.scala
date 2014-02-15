package org.littlewings.javaee7.entity

import scala.beans.BeanProperty

import javax.persistence.Cacheable
import javax.persistence.{Column, Entity, Id, Table, Version}

@SerialVersionUID(1L)
@Entity
@Table(name = "book")
@Cacheable(true)
class Book extends Serializable {
  @Id
  @Column
  @BeanProperty
  var isbn: String = _

  @Column
  @BeanProperty
  var title: String = _

  @Column
  @BeanProperty
  var price: Int = _

  @Column
  @BeanProperty
  var summary: String = _

  @Column(name = "version_no")
  @BeanProperty
  @Version
  var versionNo: Int = _

  override def toString: String =
    s"isbn = $isbn, title = $title, price = $price, summary = $summary, versionNo = $versionNo"
}
