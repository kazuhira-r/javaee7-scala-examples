package org.littlewings.javaee7.beanvalidation

import javax.validation.{Configuration, ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class MessageInterpolatorSpec extends FunSpec {
  describe("MessageInterpolator Spec") {
    it("use, my custom message interpolator") {
      val bean = new MyBean
      bean.a = "abcdef"
      bean.b = 3
      bean.c = "ABC"

      val factory = Validation.buildDefaultValidatorFactory
      val validator =
        factory
          .usingContext
          .messageInterpolator(new MyCustomMessageInterpolator(factory.getMessageInterpolator))
          .getValidator

      val constraintViolations =
        validator
          .validate(bean)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations(0).getPropertyPath.toString should be("a")
      constraintViolations(0).getMessage should be("size must be between 3 and 5")
      constraintViolations(1).getPropertyPath.toString should be("b")
      constraintViolations(1).getMessage should be("must be greater than or equal to 5")
      constraintViolations(2).getPropertyPath.toString should be("c")
      constraintViolations(2).getMessage should be("must match \"^[a-z]$\"")
    }

    it("use, my custom message interpolator, with contextual container") {
      val bean = new MyBean
      bean.a = "abcdef"
      bean.b = 3
      bean.c = "ABC"

      val configuration = Validation.byDefaultProvider.configure
      val factory =
        configuration
          .messageInterpolator(new MyCustomMessageInterpolator(configuration.getDefaultMessageInterpolator))
          .asInstanceOf[Configuration[_]]
          .buildValidatorFactory

      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(bean)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations(0).getPropertyPath.toString should be("a")
      constraintViolations(0).getMessage should be("size must be between 3 and 5")
      constraintViolations(1).getPropertyPath.toString should be("b")
      constraintViolations(1).getMessage should be("must be greater than or equal to 5")
      constraintViolations(2).getPropertyPath.toString should be("c")
      constraintViolations(2).getMessage should be("must match \"^[a-z]$\"")
    }

    it("use, my custom message interpolator, with MessageTemplate") {
      val bean = new MyBeanWithMessage
      bean.a = "abcdef"
      bean.b = 3
      bean.c = "ABC"

      val configuration = Validation.byDefaultProvider.configure
      val factory =
        configuration
          .messageInterpolator(new MyCustomMessageInterpolator(configuration.getDefaultMessageInterpolator))
          .asInstanceOf[Configuration[_]]
          .buildValidatorFactory

      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(bean)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations(0).getPropertyPath.toString should be("a")
      constraintViolations(0).getMessage should be("size must be between 3 and 5, input = abcdef")
      constraintViolations(1).getPropertyPath.toString should be("b")
      constraintViolations(1).getMessage should be("must be greater than or equal to 5, formatted input = 00003")
      constraintViolations(2).getPropertyPath.toString should be("c")
      constraintViolations(2).getMessage should be("must match \"^[a-z]$\", input = ABC")
    }

    it("using Validation.xml") {
      val bean = new MyBean
      bean.a = "abcdef"
      bean.b = 3
      bean.c = "ABC"

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(bean)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations(0).getPropertyPath.toString should be("a")
      constraintViolations(0).getMessage should be("size must be between 3 and 5")
      constraintViolations(1).getPropertyPath.toString should be("b")
      constraintViolations(1).getMessage should be("must be greater than or equal to 5")
      constraintViolations(2).getPropertyPath.toString should be("c")
      constraintViolations(2).getMessage should be("must match \"^[a-z]$\"")
    }
  }
}
