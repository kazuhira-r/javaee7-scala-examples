package org.littlewings.javaee7.entity

import scala.beans.BeanProperty

import javax.persistence.Cacheable
import javax.persistence.{Column, Entity, Id, Table, Version}

@SerialVersionUID(1L)
@Entity
@Table(name = "user")
@Cacheable(true)
class User extends Serializable {
  @Id
  @Column
  @BeanProperty
  var id: Int = _

  @Column(name = "first_name")
  @BeanProperty
  var firstName: String = _

  @Column(name = "last_name")
  @BeanProperty
  var lastName: String = _

  @Column
  @BeanProperty
  var age: Int = _

  @Column(name = "version_no")
  @BeanProperty
  @Version
  var versionNo: Int = _

  override def toString: String =
    s"id = $id, firstName = $firstName, lastName = $lastName, age = $age, versionNo = $versionNo"
}
