package org.littlewings.javaee7.cdi

import javax.enterprise.inject.spi.CDI

import org.scalatest.FunSpec

class LoginEventSpec extends FunSpec with WeldSuiteSupport {
  describe("Login Event Spec") {
    it("fire login event") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubject]).get
        loginSubject.login("カツオ", "磯野")
      }
    }

    it("fire login event by BeanManager") {
      withWeld {
        val beanManager = CDI.current.getBeanManager
        val event = new LoginEvent("ワカメ", "磯野")
        beanManager.fireEvent(event)
      }
    }
  }
}
