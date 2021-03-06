package org.littlewings.javaee7.cdi

import scala.io.Source

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CdiAlternativeSpec extends FunSpec with EmbeddedTomcatCdiSupport {
  describe("CDI Alternative Spec") {
    ignore("use HelloWorldMessageResource") {
      Source
        .fromURL(s"http://localhost:${port}/rest/message")
        .mkString should be ("Hello World")
    }

    it("use AnotherMessageResource") {
      Source
        .fromURL(s"http://localhost:${port}/rest/message")
        .mkString should be ("Another Message Service")
    }
  }
}
