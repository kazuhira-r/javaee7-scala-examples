package org.littlewings.javaee7.cdi

import javax.enterprise.inject.spi.CDI

import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DecoratorSpec extends FunSpec {
  describe("CDI Decorator Spec") {
    ignore("no Decorator") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("Hello World!")
      }
    }

    ignore("with Decorator") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("★Hello World!★")

        messageService.getClass.getSimpleName should be("DefaultMessageService$Proxy$_$$_WeldClientProxy")
      }
    }

    ignore("with Double Decorator") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("★◯Hello World!◯★")
      }
    }

    ignore("with Ordering Double Decorator") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("◯★Hello World!★◯")
      }
    }

    it("with Interceptor") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.get should be("[◯★Hello World!★◯]")
      }
    }

    it("getWith with Interceptor") {
      withWeld {
        val messageService = CDI.current.select(classOf[MessageService]).get
        messageService.getWith("{", "}") should be("{◯★Hello World!★◯}")
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
