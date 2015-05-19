package org.littlewings.javaee7

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import scala.io.Source

class MessageResourceSpec extends FunSpec with EmbeddedTomcatCdiSupport {
  describe("MessageResource Spec") {
    it("BeanManager default") {
      Source.fromURL("http://localhost:8080/rest/message/beanManagerDefault").mkString should be("Hello World")
    }

    it("BeanManager with Qualifier") {
      Source.fromURL("http://localhost:8080/rest/message/beanManagerWithQualifier").mkString should be("Hello Scala")
    }

    it("CDI Util") {
      Source.fromURL("http://localhost:8080/rest/message/cdiUtil").mkString should be("Hello Groovy")
    }

    it("Instance Lookup") {
      Source.fromURL("http://localhost:8080/rest/message/instanceLookup").mkString should be("Hello Scala")
    }
  }
}
