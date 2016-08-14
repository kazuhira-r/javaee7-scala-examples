package org.littlewings.javaee7.service

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.UserTransaction

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.littlewings.javaee7.entity.Book
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[Arquillian])
class ArquillianWithDeltaSpikeServiceIT extends JUnitSuite with Matchers {
  @Inject
  var userTransaction: UserTransaction = _

  @Inject
  var em: EntityManager = _

  @Inject
  var bookService: BookService = _

  @Before
  def setUp(): Unit = {
    userTransaction.begin()
    em.createNativeQuery("TRUNCATE TABLE book").executeUpdate()
    userTransaction.commit()
  }

  @Test
  def save(): Unit = {
    bookService.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    bookService.save(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
    bookService.save(Book("978-4798124605", "Beginning Java EE 6~GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION)", 4536))

    bookService.findByIsbn("978-4774183169").price should be(3456)
  }

  @Test
  def rollback(): Unit = {
    val thrown = the[RuntimeException] thrownBy bookService.saveFail(Book("978-4774183169", "パーフェクト Java EE", 3456))
    thrown.getMessage should be("Oops!!")

    bookService.findByIsbn("978-4774183169") should be(null)
  }

  @Test
  def usingQueryAnnotation(): Unit = {
    bookService.save(Book("978-4774183169", "パーフェクト Java EE", 3456))
    bookService.save(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
    bookService.save(Book("978-4798124605", "Beginning Java EE 6~GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION)", 4536))

    val resultBooks = bookService.findByTitleLike("%徹底入門%")
    resultBooks should have size(1)
    resultBooks.get(0).isbn should be("978-4798140926")
  }
}

object ArquillianWithDeltaSpikeServiceIT {
  @Deployment
  def createDeployment: WebArchive = {
    ShrinkWrap
      .create(classOf[WebArchive])
      .addPackages(true, "org.littlewings.javaee7")
      .addAsResource("META-INF/apache-deltaspike.properties", "META-INF/apache-deltaspike.properties")
      .addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
      .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
      .addAsLibraries(
        Maven
          .resolver
          .loadPomFromFile("pom.xml")
          .importRuntimeDependencies
          .resolve("org.scalatest:scalatest_2.11:3.0.0")
          .withTransitivity
          .asFile: _*
      )
  }
}
