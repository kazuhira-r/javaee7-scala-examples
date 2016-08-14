package org.littlewings.javaee7.service

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

import org.littlewings.javaee7.entity.Book
import org.littlewings.javaee7.repository.BookRepository

@ApplicationScoped
class BookService {
  @Inject
  var bookRepository: BookRepository = _

  @Transactional
  def save(book: Book): Book = bookRepository.save(book)

  @Transactional
  def saveFail(book: Book): Book = {
    save(book)
    throw new RuntimeException("Oops!!")
  }

  @Transactional
  def findByIsbn(isbn: String): Book = bookRepository.findBy(isbn)

  @Transactional
  def countPriceGreaterThan(price: Int): Long = bookRepository.countPriceGreaterThan(price)

  @Transactional
  def findByTitleLike(title: String): java.util.List[Book] = bookRepository.findByTitleLike(title)
}
