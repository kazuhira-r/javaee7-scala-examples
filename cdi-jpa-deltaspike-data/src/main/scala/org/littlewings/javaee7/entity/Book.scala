package org.littlewings.javaee7.entity

import javax.persistence.{Column, Entity, Id, Table}

import scala.beans.BeanProperty

object Book {
  def apply(isbn: String, title: String, price: Int): Book = {
    val book = new Book
    book.isbn = isbn
    book.title = title
    book.price = price
    book
  }
}

@Entity
@Table(name = "book")
@SerialVersionUID(1L)
class Book extends Serializable {
  @Id
  @BeanProperty
  var isbn: String = _

  @Column
  @BeanProperty
  var title: String = _

  @Column
  @BeanProperty
  var price: Int = _
}
