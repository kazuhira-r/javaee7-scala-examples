package org.littlewings.javaee7.bitronix

import bitronix.tm.TransactionManagerServices
import org.littlewings.javaee7.standalonejta.StandaloneJtaSpecSupport

class BitronixJtaSpec extends StandaloneJtaSpecSupport {
  // Init Bitronix
  override def beforeAll(): Unit =
    TransactionManagerServices.getResourceLoader.init()
}