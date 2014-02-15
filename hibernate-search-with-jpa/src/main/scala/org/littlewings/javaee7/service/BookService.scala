package org.littlewings.javaee7.service

import scala.collection.JavaConverters._

import javax.enterprise.context.RequestScoped
import javax.persistence.{EntityManager, PersistenceContext}
import javax.transaction.Transactional

import org.littlewings.javaee7.entity.Book

@Transactional
@RequestScoped
class BookService {
  @PersistenceContext
  private var em: EntityManager = _

  def create(book: Book): Unit =
    em.persist(book)

  def update(book: Book): Book =
    em.merge(book)

  def remove(book: Book): Unit =
    em.remove(em.merge(book))

  def findByIsbn(isbn: String): Book =
    em.find(classOf[Book], isbn)

  def findAll: Iterable[Book] =
    em
      .createQuery(s"""|SELECT b
                       |  FROM Book b""".stripMargin)
      .setHint("org.hibernate.cacheable", true)
      .setHint("org.hibernate.cacheMode", "NORMAL")
      .getResultList
      .asScala
      .asInstanceOf[Iterable[Book]]

  def findByOverPrice(price: Int): Iterable[Book] =
    em
      .createQuery(s"""|SELECT b
                       |  FROM Book b
                       | WHERE b.price >= :price
                       | ORDER BY b.price ASC""".stripMargin)
      .setParameter("price", price)
      .setHint("org.hibernate.cacheable", true)
      .setHint("org.hibernate.cacheMode", "NORMAL")
      .getResultList
      .asScala
      .asInstanceOf[Iterable[Book]]
}
