package org.littlewings.javaee7.batch

import org.jberet.se.Main
import org.scalatest.{Matchers, FunSpec}

class TransactionTracingSpec extends FunSpec with Matchers {
  describe("Transaction Tracing Spec") {
    it("run batch") {
      Main.main(Array("my-job"))
    }
  }
}
