package org.littlewings.javaee7.cdi.qualifier

import javax.enterprise.inject.spi.CDI

import org.littlewings.javaee7.cdi.WeldSuiteSupport
import org.scalatest.FunSpec

class LoginEventSpec extends FunSpec with WeldSuiteSupport {
  describe("Login Event Spec with Qualifier annotation") {
    it("no qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubject]).get
        loginSubject.login("カツオ", "磯野")
      }
    }

    it("with @User qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubject]).get
        loginSubject.loginAsUser("マスオ", "フグ田")
      }
    }

    it("with @Admin qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubject]).get
        loginSubject.loginAsAdmin("サザエ", "フグ田")
      }
    }
  }

  describe("Login Event Spec with select Qualifier ") {
    it("no qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubjectBySelect]).get
        loginSubject.login("カツオ", "磯野")
      }
    }

    it("with @User qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubjectBySelect]).get
        loginSubject.loginAsUser("マスオ", "フグ田")
      }
    }

    it("with @Admin qualifier") {
      withWeld {
        val loginSubject = CDI.current.select(classOf[LoginSubjectBySelect]).get
        loginSubject.loginAsAdmin("サザエ", "フグ田")
      }
    }
  }
}
