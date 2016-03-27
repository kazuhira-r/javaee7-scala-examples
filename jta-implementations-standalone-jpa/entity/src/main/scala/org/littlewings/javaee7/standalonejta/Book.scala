package org.littlewings.javaee7.standalonejta

import javax.persistence._

import scala.beans.BeanProperty

object Book {
  def apply(isbn: String, title: String, price: Int): Book = {
    val b = new Book
    b.isbn = isbn
    b.title = title
    b.price = price
    b
  }
}

@Entity
@Table(name = "book")
@SerialVersionUID(1L)
class Book extends Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Long = _

  @Column
  @BeanProperty
  var isbn: String = _

  @Column
  @BeanProperty
  var title: String = _

  @Column
  @BeanProperty
  var price: Int = _
}
