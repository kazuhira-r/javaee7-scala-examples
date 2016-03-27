package org.littlewings.javaee7.atomikos

import javax.naming.InitialContext

import com.atomikos.icatch.config.UserTransactionServiceImp
import com.atomikos.icatch.jta.{TransactionManagerImp, UserTransactionImp}
import com.atomikos.jdbc.AtomikosDataSourceBean
import org.h2.jdbcx.JdbcDataSource
import org.jnp.interfaces.NamingParser
import org.jnp.server.NamingBeanImpl
import org.littlewings.javaee7.standalonejta.StandaloneJtaSpecSupport

class AtomikosJtaSpec extends StandaloneJtaSpecSupport {
  val namingBean: NamingBeanImpl = new NamingBeanImpl

  override def beforeAll(): Unit = {
    namingBean.start()

    val context = new InitialContext

    new UserTransactionServiceImp().init()
    val transactionManager = TransactionManagerImp.getTransactionManager
    val userTransaction = new UserTransactionImp
    context.bind("java:/TransactionManager", transactionManager)
    context.bind("java:comp/UserTransaction", userTransaction)

    namingBean.getNamingInstance.createSubcontext(new NamingParser().parse("jdbc"))

    val atomikosDataSourceBean = new AtomikosDataSourceBean
    atomikosDataSourceBean.setUniqueResourceName("jdbc/h2Ds")
    atomikosDataSourceBean.setXaDataSourceClassName(classOf[JdbcDataSource].getName)
    val properties = new java.util.Properties
    properties.put("URL", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    atomikosDataSourceBean.setXaProperties(properties)
    context.bind("java:/jdbc/h2Ds", atomikosDataSourceBean)
  }

  override def afterAll(): Unit = {
    namingBean.stop()
  }
}
