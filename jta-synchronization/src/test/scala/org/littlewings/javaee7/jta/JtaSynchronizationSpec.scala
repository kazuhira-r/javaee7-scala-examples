package org.littlewings.javaee7.jta

import javax.naming.InitialContext
import javax.persistence.{NoResultException, Persistence}
import javax.transaction.{Status, TransactionManager, UserTransaction}

import com.arjuna.ats.jta.common.jtaPropertyManager
import com.arjuna.ats.jta.utils.JNDIManager
import org.h2.jdbcx.JdbcDataSource
import org.jnp.interfaces.NamingParser
import org.jnp.server.NamingBeanImpl
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec, Matchers}

class JtaSynchronizationSpec extends FunSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll {
  val namingBean: NamingBeanImpl = new NamingBeanImpl

  override def beforeAll(): Unit = {
    namingBean.start()

    JNDIManager.bindJTATransactionManagerImplementation()

    namingBean.getNamingInstance.createSubcontext(new NamingParser().parse("jboss"))
    jtaPropertyManager.getJTAEnvironmentBean.setTransactionManagerJNDIContext("java:/jboss/TransactionManager")
    jtaPropertyManager
      .getJTAEnvironmentBean
      .setTransactionSynchronizationRegistryJNDIContext("java:/jboss/TransactionSynchronizationRegistry")
    jtaPropertyManager
      .getJTAEnvironmentBean
      .setUserTransactionJNDIContext("java:comp/UserTransaction")

    JNDIManager.bindJTATransactionManagerImplementation()
    JNDIManager.bindJTAUserTransactionImplementation()

    namingBean.getNamingInstance.createSubcontext(new NamingParser().parse("jdbc"))

    val dataSource = new JdbcDataSource
    dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    val context = new InitialContext
    context.bind("java:/jdbc/h2Ds", dataSource)
  }

  override def afterAll(): Unit = {
    namingBean.stop()
  }

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

  describe("JTA Synchronization Spec") {
    it("JTA with Synchronization, commit") {
      val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
      val em = emf.createEntityManager

      val transactionManager = new InitialContext().lookup("java:/jboss/TransactionManager").asInstanceOf[TransactionManager]
      transactionManager.getTransaction should be (null)

      transactionManager.begin()

      transactionManager.getTransaction should not be(null)

      val synchronization = new MySynchronization

      val tx = transactionManager.getTransaction
      tx.registerSynchronization(synchronization)

      em.persist(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
      em.persist(Book("978-4798042169", "わかりやすいJavaEEウェブシステム入門", 3456))
      em.persist(Book("978-4798124605", "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION) ", 4536))

      println("commit, start")
      transactionManager.commit()
      println("commit, end")

      synchronization.getCalledBeforeCompletion should be(true)
      synchronization.getAfterCompletionStatus should be(Status.STATUS_COMMITTED)

      val query =
        em
          .createQuery("SELECT b FROM Book b WHERE isbn = :isbn", classOf[Book])
          .setParameter("isbn", "978-4798140926")

      query.getSingleResult.title should be("Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築")

      em.close()
      emf.close()
    }

    it("JTA with Synchronization, rollback") {
      val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
      val em = emf.createEntityManager

      val transactionManager = new InitialContext().lookup("java:/jboss/TransactionManager").asInstanceOf[TransactionManager]
      transactionManager.getTransaction should be (null)

      transactionManager.begin()

      transactionManager.getTransaction should not be(null)

      val synchronization = new MySynchronization

      val tx = transactionManager.getTransaction
      tx.registerSynchronization(synchronization)

      em.persist(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
      em.persist(Book("978-4798042169", "わかりやすいJavaEEウェブシステム入門", 3456))
      em.persist(Book("978-4798124605", "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION) ", 4536))

      println("rollback, start")
      transactionManager.rollback()
      println("rollback, end")

      synchronization.getCalledBeforeCompletion should be(false)
      synchronization.getAfterCompletionStatus should be(Status.STATUS_ROLLEDBACK)

      val query =
        em
          .createQuery("SELECT b FROM Book b WHERE isbn = :isbn", classOf[Book])
          .setParameter("isbn", "978-4798140926")

      val thrown = the[NoResultException] thrownBy query.getSingleResult
      thrown.getMessage should be("No entity found for query")

      em.close()
      emf.close()
    }

    it("JTA with Synchronization, suspend/resume/commit") {
      val emf = Persistence.createEntityManagerFactory("javaee7.standalone.jta.pu")
      val em = emf.createEntityManager

      val transactionManager = new InitialContext().lookup("java:/jboss/TransactionManager").asInstanceOf[TransactionManager]
      transactionManager.getTransaction should be (null)

      transactionManager.begin()

      transactionManager.getTransaction should not be(null)

      val synchronization = new MySynchronization

      val tx = transactionManager.getTransaction
      tx.registerSynchronization(synchronization)

      em.persist(Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104))
      em.persist(Book("978-4798042169", "わかりやすいJavaEEウェブシステム入門", 3456))
      em.persist(Book("978-4798124605", "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava (Programmer's SELECTION) ", 4536))

      println("suspend, start")
      val suspendedTx = transactionManager.suspend()
      println("suspend, end")

      synchronization.getCalledBeforeCompletion should be(false)
      synchronization.getAfterCompletionStatus should be(-1)

      suspendedTx should be theSameInstanceAs(tx)

      println("resume, start")
      transactionManager.resume(suspendedTx)
      println("resume, end")

      synchronization.getCalledBeforeCompletion should be(false)
      synchronization.getAfterCompletionStatus should be(-1)

      println("commit, start")
      transactionManager.commit()
      println("commit, end")

      synchronization.getCalledBeforeCompletion should be(true)
      synchronization.getAfterCompletionStatus should be(Status.STATUS_COMMITTED)

      val query =
        em
          .createQuery("SELECT b FROM Book b WHERE isbn = :isbn", classOf[Book])
          .setParameter("isbn", "978-4798140926")

      query.getSingleResult.title should be("Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築")

      em.close()
      emf.close()
    }
  }
}
