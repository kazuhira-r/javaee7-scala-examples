package org.littlewings.javaee7.standalonejta

import javax.naming.InitialContext
import javax.persistence.{NoResultException, Persistence}
import javax.transaction.UserTransaction

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec, Matchers}

abstract class StandaloneJtaSpecSupport extends FunSpec with BeforeAndAfter with BeforeAndAfterAll with Matchers {
  before {
    val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
    val em = emf.createEntityManager
    val userTransaction = new InitialContext().lookup("java:comp/UserTransaction").asInstanceOf[UserTransaction]
    userTransaction.begin()
    em.createNativeQuery("TRUNCATE TABLE book").executeUpdate()
    userTransaction.commit()
    em.close()
    emf.close()
  }

  describe(s"${getClass.getSimpleName.replace("JtaSpec", "")} JTA Spec") {
    it("simple JPA, commit") {
      val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
      val em = emf.createEntityManager

      val userTransaction = new InitialContext().lookup("java:comp/UserTransaction").asInstanceOf[UserTransaction]
      userTransaction.begin()

      em.persist(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
      em.persist(Book("978-4798042169", "わかりやすいJavaEEウェブシステム入門", 3456))
      em.persist(Book("978-4798124605", "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION) ", 4536))

      userTransaction.commit()

      val query =
        em
          .createQuery("SELECT b FROM Book b WHERE isbn = :isbn", classOf[Book])
          .setParameter("isbn", "978-4798140926")

      query.getSingleResult.title should be("Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築")

      em.close()
      emf.close()
    }

    it("use simple JPA, rollback") {
      val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
      val em = emf.createEntityManager

      val userTransaction = new InitialContext().lookup("java:comp/UserTransaction").asInstanceOf[UserTransaction]
      userTransaction.begin()

      em.persist(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
      em.persist(Book("978-4798042169", "わかりやすいJavaEEウェブシステム入門", 3456))
      em.persist(Book("978-4798124605", "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION) ", 4536))

      userTransaction.rollback()

      val query =
        em
          .createQuery("SELECT b FROM Book b WHERE isbn = :isbn", classOf[Book])
          .setParameter("isbn", "978-4798140926")

      val thrown = the[NoResultException] thrownBy query.getSingleResult
      thrown.getMessage should be("No entity found for query")

      em.close()
      emf.close()
    }
  }
}
