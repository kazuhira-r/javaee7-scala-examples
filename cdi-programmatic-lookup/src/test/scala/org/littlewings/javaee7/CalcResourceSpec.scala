package org.littlewings.javaee7

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import scala.io.Source

class CalcResourceSpec extends FunSpec with EmbeddedTomcatCdiSupport {
  describe("CalcResource Spec") {
    it("Inject BeanManager") {
      Source.fromURL("http://localhost:8080/rest/calc/beanManagerInject?a=1&b=2").mkString should be("3")
    }

    it("JNDI Lookup BeanManager") {
      Source.fromURL("http://localhost:8080/rest/calc/beanManagerLookup?a=1&b=2").mkString should be("3")
    }

    it("CDI Util") {
      Source.fromURL("http://localhost:8080/rest/calc/cdiUtil?a=1&b=2").mkString should be("3")
    }

    it("Instance Lookup") {
      Source.fromURL("http://localhost:8080/rest/calc/instanceLookup?a=1&b=2").mkString should be("3")
    }
  }
}
