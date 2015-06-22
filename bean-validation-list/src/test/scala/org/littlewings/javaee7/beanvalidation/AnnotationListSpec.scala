package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class AnnotationListSpec extends FunSpec {
  describe("AnnotationList Spec") {
    it("Default Group, valid") {
      val person = new Person
      person.firstName = "カツオ"
      person.lastName = "磯野"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(person)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }

    it("Default Group, invalid case 1") {
      val person = new Person
      person.firstName = "カツオ"
      person.lastName = "磯の"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(person)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (1)
      constraintViolations(0).getPropertyPath.toString should be("lastName")
      constraintViolations(0).getMessage should be("must match \".*野$\"")
    }

    it("Default Group, invalid case 2") {
      val person = new Person
      person.firstName = "カツオ？"
      person.lastName = "いそ野"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(person)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (2)
      constraintViolations(0).getPropertyPath.toString should be("firstName")
      constraintViolations(0).getMessage should be("size must be between 3 and 3")
      constraintViolations(1).getPropertyPath.toString should be("lastName")
      constraintViolations(1).getMessage should be("must match \"^磯.*\"")
    }

    it("MyGroup, valid") {
      val person = new Person
      person.firstName = "katsuo"
      person.lastName = "isono"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(person, classOf[MyGroup])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }

    it("MyGroup, invalid") {
      val person = new Person
      person.firstName = "isono katsuo"
      person.lastName = "isono"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(person, classOf[MyGroup])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size(1)
      constraintViolations(0).getPropertyPath.toString should be("firstName")
      constraintViolations(0).getMessage should be("size must be between 4 and 6")
    }
  }
}
