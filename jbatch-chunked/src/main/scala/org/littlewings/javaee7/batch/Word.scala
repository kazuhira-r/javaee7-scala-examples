package org.littlewings.javaee7.batch

import javax.persistence.{Column, Id, Entity, Table}

import scala.beans.BeanProperty

object Word {
  def apply(token: String, count: Int = 1): Word = {
    val w = new Word
    w.token = token
    w.count = count
    w
  }
}

@Entity
@Table(name = "word")
@SerialVersionUID(1L)
class Word extends Serializable {
  @Id
  @BeanProperty
  var token: String = _

  @Column(name = "count")
  var count: Int = _
}
