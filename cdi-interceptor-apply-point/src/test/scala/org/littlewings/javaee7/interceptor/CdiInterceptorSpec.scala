package org.littlewings.javaee7.interceptor

import javax.enterprise.inject.spi.CDI

import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CdiInterceptorSpec extends FunSpec {
  describe("CDI Interceptor Spec") {
    it("Normal Scoped") {
      withWeld {
        val messageService = CDI.current.select(classOf[NormalScopedMessageService]).get

        messageService.join(", ", "Hello", "World") should be("★Hello, World★")
      }
    }

    it("Pseudo Scoped") {
      withWeld {
        val messageService = CDI.current.select(classOf[PseudoScopedMessageService]).get

        messageService.join(", ", "Hello", "World") should be("★Hello, World★")
      }
    }

    it("Normal Scoped, Method Call Chain") {
      withWeld {
        val messageService = CDI.current.select(classOf[NormalScopedMessageService]).get

        messageService.joinChain(", ", "Hello", "World") should be("★Hello, World★")
      }
    }

    it("Pseudo Scoped, Method Call Chain") {
      withWeld {
        val messageService = CDI.current.select(classOf[PseudoScopedMessageService]).get

        messageService.joinChain(", ", "Hello", "World") should be("★Hello, World★")
      }
    }
    
    it("Object Chain") {
      withWeld {
        val messageService = CDI.current.select(classOf[MixedMessageService]).get

        messageService.joinChain(", ", "Hello", "World") should be("★★Hello, World★★")
        messageService.joinChainToPackagePrivate(", ", "Hello", "World") should be("★★Hello, World★★")
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
