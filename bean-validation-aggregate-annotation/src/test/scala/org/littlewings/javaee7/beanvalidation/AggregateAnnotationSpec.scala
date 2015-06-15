package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class AggregateAnnotationSpec extends FunSpec {
  describe("AggregateAnnotationSpec") {
    it("null") {
      val user = new User

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(user)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (2)
      constraintViolations(0).getPropertyPath.toString should be("id")
      constraintViolations(0).getMessage should be("may not be null")
      constraintViolations(1).getPropertyPath.toString should be("idValidAggregate")
      constraintViolations(1).getMessage should be("may not be null")
    }

    it("invalid user id") {
      val user = new User
      user.id = "ab"
      user.idValidAggregate = "ab"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(user)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (3)
      constraintViolations(0).getPropertyPath.toString should be("id")
      constraintViolations(0).getMessage should be("must match \"[A-Z0-9]+\"")
      constraintViolations(1).getPropertyPath.toString should be("id")
      constraintViolations(1).getMessage should be("size must be between 3 and 5")

      constraintViolations(2).getPropertyPath.toString should be("idValidAggregate")
      constraintViolations(2).getMessage should be("ユーザーIDの形式が間違ってます")
    }

    it("user id") {
      val user = new User
      user.id = "AB001"
      user.idValidAggregate = "AB001"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(user)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }
  }
}
