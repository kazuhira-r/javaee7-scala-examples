package org.littlewings.javaee7.repository

import org.apache.deltaspike.data.api.{EntityRepository, Query, Repository}
import org.littlewings.javaee7.entity.Book

@Repository
trait BookRepository extends EntityRepository[Book, String] {
  def findByPriceGreaterThanEqualsOrderByPriceDesc(price: Int): java.util.List[Book]

  @Query("SELECT COUNT(b) FROM Book b WHERE b.price >= ?1")
  def countPriceGreaterThan(price: Int): Long

  @Query("SELECT b FROM Book b WHERE b.title LIKE ?1 ORDER BY b.price DESC")
  def findByTitleLike(title: String): java.util.List[Book]
}
