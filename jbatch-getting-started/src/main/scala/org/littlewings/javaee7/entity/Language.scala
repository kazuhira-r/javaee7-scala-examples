package org.littlewings.javaee7.entity

import javax.persistence.{Column, Entity, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "language")
@SerialVersionUID(1L)
class Language extends Serializable {
  @Id
  @BeanProperty
  var id: Long = _

  @Column
  @BeanProperty
  var name: String = _

  override def toString: String =
    s"Language id[${id}], name[${name}]"
}
