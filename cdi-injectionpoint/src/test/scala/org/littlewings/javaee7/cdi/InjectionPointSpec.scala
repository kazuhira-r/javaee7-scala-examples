package org.littlewings.javaee7.cdi

import javax.enterprise.inject.spi.CDI
import javax.enterprise.util.AnnotationLiteral

import org.jboss.logging.Logger
import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class InjectionPointSpec extends FunSpec {
  describe("InjectionPoint Spec") {
    it("trace simple") {
      withWeld {
        val calcService = CDI.current.select(classOf[CalcService]).get
        calcService.add(2, 3) should be(5)
      }
    }

    it("with Qualifier, Stereotypes") {
      withWeld {
        val calcService = CDI.current.select(classOf[CalcService], new AnnotationLiteral[ServiceQualifier] {}).get
        calcService.add(2, 3) should be(5)
      }
    }

    it("manual lookup") {
      withWeld {
        val logger = CDI.current.select(classOf[Logger], new AnnotationLiteral[ManualLoggerQualifier] {}).get
        logger.infof("get Logger by CDI.")
      }
    }
  }

  protected def withWeld(f: => Unit): Unit = {
    val weld = new Weld

    try {
      weld.initialize()

      f
    } finally {
      weld.shutdown()
    }
  }
}
