package org.littlewings.javaee7.beanvalidation

import javax.validation.groups.Default
import javax.validation.{ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class GroupsValidationSpec extends FunSpec {
  describe("GroupsValidation Spec") {
    it("implicit Default") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (4)
    }


    it("explicit Default") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[Default])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (4)
    }

    it("Extends Default") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[ExtendsDefault])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (6)
    }

    it("group A") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[A])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (3)
    }

    it("Default, group A") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[Default], classOf[A])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (6)
    }

    it("Default, group B") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[Default], classOf[B])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (5)
    }

    it("group A, B") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[A], classOf[B])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (4)
    }

    it("group B, A") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[B], classOf[A])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (4)
    }

    it("group sequence Default, A") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[DefaultWithA])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (4)
    }

    it("group sequence A, B") {
      val target = new SomeClass
      target.field1 = "field1-ABC123"
      target.field2 = "field2-ABC123"
      target.field3 = "field3-ABC123"
      target.field4 = "field4-ABC123"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(target, classOf[AwithB])
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (3)
    }
  }
}
