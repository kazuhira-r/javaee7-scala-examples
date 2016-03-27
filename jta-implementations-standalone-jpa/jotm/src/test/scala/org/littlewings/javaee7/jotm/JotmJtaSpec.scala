package org.littlewings.javaee7.jotm

import java.rmi.registry.LocateRegistry
import javax.naming.InitialContext

import org.enhydra.jdbc.standard.StandardXADataSource
import org.h2.Driver
import org.littlewings.javaee7.standalonejta.StandaloneJtaSpecSupport
import org.objectweb.jotm.Jotm

class JotmJtaSpec extends StandaloneJtaSpecSupport {
  val jotm: Jotm = new Jotm(true, false)

  override def beforeAll(): Unit = {
    LocateRegistry.createRegistry(1099)

    val dataSource = new StandardXADataSource
    dataSource.setTransactionManager(jotm.getTransactionManager)
    dataSource.setDriverName(classOf[Driver].getName)
    dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")

    val context = new InitialContext
    context.bind("java:/TransactionManager", jotm.getTransactionManager)
    context.bind("java:comp/UserTransaction", jotm.getUserTransaction)
    context.bind("java:/h2Ds", dataSource)
  }

  override def afterAll(): Unit = {
    jotm.stop()
  }
}
