package org.littlewings.javaee7.beanvalidation

import scala.collection.JavaConverters._

import javax.validation.{Validation, ValidatorFactory}
import javax.validation.constraints.{Min, NotNull, Size}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class BeanValidationGettingStartedSpec extends FunSpec {
  describe("bean-validation simple spec") {
    it("normal") {
      val book = Book("978-4798124605",
                      "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                      4536)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should be (empty)
    }

    it("required, negative") {
      val book = Book(null,
                      "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                      -1)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should have size 2

      val violations =
        constraintViolations
          .toArray
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      val v0 = violations(0)
      v0.getPropertyPath.toString should be ("isbn")

      val v1 = violations(1)
      v1.getPropertyPath.toString should be ("price")

      s"${v0.getPropertyPath}${v0.getMessage}" should be ("isbnは必須です")
      s"${v1.getPropertyPath}${v1.getMessage}" should be ("priceは1以上でなければなりません")

      v0.getConstraintDescriptor.getAnnotation should be (a [NotNull])
      v1.getConstraintDescriptor.getAnnotation should be (a [Min])
    }

    it("size") {
      val book = Book("12345",
                      "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                      4536)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should have size 1

      val violations = constraintViolations.toArray

      val v0 = violations(0)
      v0.getPropertyPath.toString should be ("isbn")

      s"${v0.getPropertyPath}${v0.getMessage}" should be ("isbnのサイズは、14以上14以下でなければなりません")

      v0.getConstraintDescriptor.getAnnotation should be (a [Size])
    }
  }

  describe("bean-validation case-class spec") {
    it("normal") {
      val book = CaseBook("978-4798124605",
                          "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                          4536)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should be (empty)
    }

    it("required, negative") {
      val book = CaseBook(null,
                          "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                          -1)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should have size 2

      val violations =
        constraintViolations
          .toArray
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      val v0 = violations(0)
      v0.getPropertyPath.toString should be ("isbn")

      val v1 = violations(1)
      v1.getPropertyPath.toString should be ("price")

      s"${v0.getPropertyPath}${v0.getMessage}" should be ("isbnは必須です")
      s"${v1.getPropertyPath}${v1.getMessage}" should be ("priceは1以上でなければなりません")

      v0.getConstraintDescriptor.getAnnotation should be (a [NotNull])
      v1.getConstraintDescriptor.getAnnotation should be (a [Min])
    }

    it("size") {
      val book = CaseBook("12345",
                          "Beginning Java EE 6 GlassFish 3で始めるエンタープライズJava",
                          4536)

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(book).asScala

      constraintViolations should have size 1

      val violations = constraintViolations.toArray

      val v0 = violations(0)
      v0.getPropertyPath.toString should be ("isbn")

      s"${v0.getPropertyPath}${v0.getMessage}" should be ("isbnのサイズは、14以上14以下でなければなりません")

      v0.getConstraintDescriptor.getAnnotation should be (a [Size])
    }
  }

  describe("complex constraint") {
    it("test") {
      val bean = Bean("100")

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations = validator.validate(bean).asScala

      constraintViolations should have size 2

      val violations =
        constraintViolations
          .toArray
          .sortWith(_.getMessageTemplate < _.getMessageTemplate)

      val v0 = violations(0)
      v0.getPropertyPath.toString should be ("value")

      val v1 = violations(1)
      v1.getPropertyPath.toString should be ("value")

      s"${v0.getPropertyPath}${v0.getMessage}" should be ("valueは境界以外の数値（予測:<2digits>.<0digits>）")
      s"${v1.getPropertyPath}${v1.getMessage}" should be ("valueのサイズは、1000以上9999以下でなければなりません")
    }
  }
}
