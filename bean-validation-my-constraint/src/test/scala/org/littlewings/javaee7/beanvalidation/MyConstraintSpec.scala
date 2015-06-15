package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class MyConstraintSpec extends FunSpec {
  describe("MyConstraint Spec") {
    it("not null") {
      val isono = new Isono

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(isono)
          .toArray(Array.empty[ConstraintViolation[Any]])

      constraintViolations should have size (1)
      constraintViolations.map(_.getMessage) should contain only ("may not be null")
    }

    it("size & select") {
      val isono = new Isono
      isono.name = "フグ田サザエ"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(isono)
          .toArray(Array.empty[ConstraintViolation[Any]])

      constraintViolations should have size (2)
      constraintViolations
        .map(_.getMessage) should contain only(
        "[カツオ, ワカメ, 波平, フネ]のいずれかから選択してください",
        "size must be between 1 and 3"
        )
    }

    it("select") {
      val isono = new Isono
      isono.name = "サザエ"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(isono)
          .toArray(Array.empty[ConstraintViolation[Any]])

      constraintViolations should have size (1)
      constraintViolations.map(_.getMessage) should contain only ("[カツオ, ワカメ, 波平, フネ]のいずれかから選択してください")
    }

    it("valid") {
      val isono = new Isono
      isono.name = "カツオ"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(isono)
          .toArray(Array.empty[ConstraintViolation[Any]])

      constraintViolations should be(empty)
    }
  }
}
