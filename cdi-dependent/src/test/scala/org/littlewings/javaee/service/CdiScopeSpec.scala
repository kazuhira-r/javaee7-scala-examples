package org.littlewings.javaee.service

import javax.enterprise.inject.spi.CDI

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CdiScopeSpec extends FunSpec with WeldSpecSupport {
  describe("CDI Scope spec") {
    it("Normal Scope") {
      withWeld {
        val service1 = CDI.current.select(classOf[NormalScopedCalcService]).get
        val service2 = CDI.current.select(classOf[NormalScopedCalcService]).get

        service1 should be theSameInstanceAs (service2)
      }
    }

    it("Pseudo Scope") {
      withWeld {
        val service1 = CDI.current.select(classOf[PseudoScopedCalcService]).get
        val service2 = CDI.current.select(classOf[PseudoScopedCalcService]).get

        service1 should not be theSameInstanceAs(service2)
      }
    }

    it("Normal Scope with Pseudo Scope") {
      withWeld {
        val service1 = CDI.current.select(classOf[NormalScopedMixedCalcService]).get
        val service2 = CDI.current.select(classOf[NormalScopedMixedCalcService]).get

        service1 should be theSameInstanceAs (service2)
        service1.delegate should be theSameInstanceAs (service2.delegate)
      }
    }

    it("Pseudo Scope with Normal Scope") {
      withWeld {
        val service1 = CDI.current.select(classOf[PseudoScopedMixedCalcService]).get
        val service2 = CDI.current.select(classOf[PseudoScopedMixedCalcService]).get

        service1 should not be theSameInstanceAs (service2)
        service1.delegate should be theSameInstanceAs (service2.delegate)
      }
    }

    it("Pseudo Scope with Pseudo Scope") {
      withWeld {
        val service1 = CDI.current.select(classOf[PseudoScopedWithPseudoScopedMixedCalcService]).get
        val service2 = CDI.current.select(classOf[PseudoScopedWithPseudoScopedMixedCalcService]).get

        service1 should not be theSameInstanceAs (service2)
        service1.delegate should not be theSameInstanceAs (service2.delegate)
      }
    }

    it("Normal Scope, Client Proxies?") {
      withWeld {
        val service = CDI.current.select(classOf[NormalScopedCalcService]).get

        service.add(1, 2) should be ((3, "NormalScopedCalcService"))

        service.getClass.getPackage.getName should be(classOf[NormalScopedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("NormalScopedCalcService$Proxy$_$$_WeldClientProxy")
        service.getClass.getSuperclass.getSimpleName should be("NormalScopedCalcService")
      }
    }

    it("Pseudo Scope, Client Proxies?") {
      withWeld {
        val service = CDI.current.select(classOf[PseudoScopedCalcService]).get

        service.add(1, 2) should be ((3, "PseudoScopedCalcService"))

        service.getClass.getPackage.getName should be(classOf[PseudoScopedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("PseudoScopedCalcService")
      }
    }

    it("Normal Scope with Pseudo Scope, Client Proxies?") {
      withWeld {
        val service = CDI.current.select(classOf[NormalScopedMixedCalcService]).get

        service.add(1, 2) should be ((3, "NormalScopedMixedCalcService"))

        service.getClass.getPackage.getName should be(classOf[NormalScopedMixedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("NormalScopedMixedCalcService$Proxy$_$$_WeldClientProxy")
        service.getClass.getSuperclass.getSimpleName should be("NormalScopedMixedCalcService")

        service.delegate.getClass.getPackage.getName should be(classOf[PseudoScopedCalcService].getPackage.getName)
        service.delegate.getClass.getSimpleName should be("PseudoScopedCalcService")
      }
    }

    it("Pseudo Scope with Normal Scope, Client Proxies?") {
      withWeld {
        val service = CDI.current.select(classOf[PseudoScopedMixedCalcService]).get

        service.add(1, 2) should be ((3, "PseudoScopedMixedCalcService"))

        service.getClass.getPackage.getName should be(classOf[PseudoScopedMixedCalcService].getPackage.getName)
        service.getClass.getSimpleName should be("PseudoScopedMixedCalcService")

        service.delegate.getClass.getPackage.getName should be(classOf[NormalScopedCalcService].getPackage.getName)
        service.delegate.getClass.getSimpleName should be("NormalScopedCalcService$Proxy$_$$_WeldClientProxy")
        service.delegate.getClass.getSuperclass.getSimpleName should be("NormalScopedCalcService")
      }
    }
  }
}
