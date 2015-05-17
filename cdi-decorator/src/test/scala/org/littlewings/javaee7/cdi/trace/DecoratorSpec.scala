package org.littlewings.javaee7.cdi.trace

import javax.enterprise.inject.spi.CDI

import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DecoratorSpec extends FunSpec {
  describe("CDI Decorator Spec") {
    it("with Decorator") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("Hello World!")
      }
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
