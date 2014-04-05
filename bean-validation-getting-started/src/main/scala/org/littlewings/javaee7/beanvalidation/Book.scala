package org.littlewings.javaee7.beanvalidation

import javax.validation.constraints.{Min, NotNull, Size}

object Book {
  def apply(isbn: String, title: String, price: Int): Book = {
    val book = new Book
    book.isbn = isbn
    book.title = title
    book.price = price
    book
  }
}

class Book {
  @NotNull
  @Size(min = 14, max = 14)
  var isbn: String = _

  @NotNull
  var title: String = _

  @Min(1)
  var price: Int = _
}
