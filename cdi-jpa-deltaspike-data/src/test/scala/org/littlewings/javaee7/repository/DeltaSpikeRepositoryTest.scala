package org.littlewings.javaee7.repository

import javax.inject.Inject
import javax.persistence.EntityManager

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.littlewings.javaee7.entity.Book
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class DeltaSpikeRepositoryTest extends JUnitSuite with Matchers {
  @Inject
  var bookRepository: BookRepository = _

  @Inject
  var em: EntityManager = _

  @Before
  def setUp(): Unit = {
    val tx = em.getTransaction
    tx.begin()
    em.createNativeQuery("TRUNCATE TABLE book").executeUpdate()
    tx.commit()
  }

  @Test
  def save(): Unit = {
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    bookRepository.save(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
    bookRepository.save(Book("978-4798124605", "Beginning Java EE 6~GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION)", 4536))

    bookRepository.count should be(3L)
  }

  @Test
  def findByPrimaryKey(): Unit = {
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 3456))

    bookRepository.findBy("978-4774183169").price should be(3456)
  }

  @Test
  def update(): Unit = {
    val tx = em.getTransaction
    tx.begin()
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    tx.commit()

    bookRepository.findBy("978-4774183169").price should be(3456)

    val tx2 = em.getTransaction
    tx2.begin()
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 4456))
    tx2.commit()

    bookRepository.findBy("978-4774183169").price should be(4456)
  }

  @Test
  def usingMethodExpressions(): Unit = {
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    bookRepository.save(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
    bookRepository.save(Book("978-4798124605", "Beginning Java EE 6~GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION)", 4536))

    val resultBooks = bookRepository.findByPriceGreaterThanEqualsOrderByPriceDesc(4000)
    resultBooks should have size (2)
    resultBooks.get(0).isbn should be("978-4798124605") // Begging Java EE 6
    resultBooks.get(1).isbn should be("978-4798140926") // Java EE 7
  }

  @Test
  def usingQueryAnnotation(): Unit = {
    bookRepository.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    bookRepository.save(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
    bookRepository.save(Book("978-4798124605", "Beginning Java EE 6~GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION)", 4536))

    bookRepository.countPriceGreaterThan(4000) should be(2L)

    val resultBooks = bookRepository.findByTitleLike("%GlassFish%")
    resultBooks should have size (1)
    resultBooks.get(0).isbn should be("978-4798124605")
  }
}
