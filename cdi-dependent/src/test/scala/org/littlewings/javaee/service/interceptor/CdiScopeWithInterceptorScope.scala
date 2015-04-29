package org.littlewings.javaee.service.interceptor

import javax.enterprise.inject.spi.CDI

import org.littlewings.javaee.service.WeldSpecSupport
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CdiScopeWithInterceptorScope extends FunSpec with WeldSpecSupport {
  describe("CDI Scope with Interceptor spec") {
    it("Normal Scope") {
      withWeld {
        val service = CDI.current.select(classOf[NormalScopedCalcService]).get

        service.add(1, 2) should be((3, "NormalScopedCalcService$Proxy$_$$_WeldSubclass"))

        service.getClass.getPackage.getName should be(classOf[NormalScopedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("NormalScopedCalcService$Proxy$_$$_WeldClientProxy")
        service.getClass.getSuperclass.getSimpleName should be("NormalScopedCalcService")
      }
    }

    it("Pseudo Scope") {
      withWeld {
        val service = CDI.current.select(classOf[PseudoScopedCalcService]).get

        service.add(1, 2) should be((3, "PseudoScopedCalcService$Proxy$_$$_WeldSubclass"))

        service.getClass.getPackage.getName should be(classOf[PseudoScopedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("PseudoScopedCalcService$Proxy$_$$_WeldSubclass")
        service.getClass.getSuperclass.getSimpleName should be("PseudoScopedCalcService")
      }
    }

    it("Normal Scope with Pseudo Scope") {
      val thrown =
        the[IllegalStateException] thrownBy CDI.current.select(classOf[NormalScopedMixedCalcService]).get
      thrown.getMessage should be ("Singleton not set for STATIC_INSTANCE => []")
    }

    it("Pseudo Scope with Normal Scope") {
      withWeld {
        val service = CDI.current.select(classOf[PseudoScopedMixedCalcService]).get

        service.add(1, 2) should be((3, "PseudoScopedMixedCalcService$Proxy$_$$_WeldSubclass"))

        service.getClass.getPackage.getName should be(classOf[PseudoScopedMixedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("PseudoScopedMixedCalcService$Proxy$_$$_WeldSubclass")
        service.getClass.getSuperclass.getSimpleName should be("PseudoScopedMixedCalcService")
      }
    }
  }
}
