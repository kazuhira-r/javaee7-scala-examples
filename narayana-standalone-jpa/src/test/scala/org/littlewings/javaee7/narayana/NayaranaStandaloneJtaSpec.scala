package org.littlewings.javaee7.narayana

import javax.naming.InitialContext
import javax.persistence.{NoResultException, Persistence}
import javax.transaction.UserTransaction

import com.arjuna.ats.jta.common.jtaPropertyManager
import com.arjuna.ats.jta.utils.JNDIManager
import org.h2.jdbcx.JdbcDataSource
import org.jnp.interfaces.NamingParser
import org.jnp.server.NamingBeanImpl
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec, Matchers}

class NayaranaStandaloneJtaSpec extends FunSpec with BeforeAndAfter with BeforeAndAfterAll with Matchers {
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

  describe("Nayarana Standalone JTA Spec") {
    it("use simple JPA, commit") {
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
