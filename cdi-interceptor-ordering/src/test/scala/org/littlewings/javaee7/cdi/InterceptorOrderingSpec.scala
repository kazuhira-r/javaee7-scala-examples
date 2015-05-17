package org.littlewings.javaee7.cdi

import javax.enterprise.inject.spi.CDI
import javax.interceptor.Interceptor

import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class InterceptorOrderingSpec extends FunSpec {
  describe("Interceptor ordering Spec") {
    it("apply interceptor") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.print()
      }
    }

    it("pre defined Priority") {
      Interceptor.Priority.PLATFORM_BEFORE should be(0)
      Interceptor.Priority.LIBRARY_BEFORE should be(1000)
      Interceptor.Priority.APPLICATION should be(2000)
      Interceptor.Priority.LIBRARY_AFTER should be(3000)
      Interceptor.Priority.PLATFORM_AFTER should be(4000)
    }
  }

  private def withWeld(f: => Unit): Unit = {
    val weld = new Weld

    try {
      weld.initialize()

      f
    } finally {
      weld.shutdown()
    }
  }
}
