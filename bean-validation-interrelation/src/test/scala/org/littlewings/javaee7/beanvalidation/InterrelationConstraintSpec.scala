package org.littlewings.javaee7.beanvalidation

import scala.collection.JavaConverters._

import javax.validation.{ConstraintViolation, Validation}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class InterrelationConstraintSpec extends FunSpec {
  describe("InterrelationConstraint Spec") {
    it("invalid manager") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "課長"
      employee.salary = 30000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (1)
      constraintViolations(0).getPropertyPath.toString should be("salary")
      constraintViolations(0).getMessage should be("課長の給料は、50000以上でなければなりません")
    }

    it("valid manager") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "課長"
      employee.salary = 50000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }

    it("invalid general manager") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "部長"
      employee.salary = 90000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (1)
      constraintViolations(0)
        .getPropertyPath
        .iterator
        .asScala
        .map(_.toString)
        .toArray should contain only("post", "salary")
      constraintViolations(0).getMessage should be("部長の給料は、100000以上でなければなりません")
    }

    it("valid general manager") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "部長"
      employee.salary = 100000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }

    it("invalid staff") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "平社員"
      employee.salary = 50000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should have size (1)
      constraintViolations(0).getPropertyPath.toString should be("salary")
      constraintViolations(0).getMessage should be("平社員の給料は、50000より低くなければなりません")
    }

    it("valid staff") {
      val employee = new Employee
      employee.name = "次郎"
      employee.post = "平社員"
      employee.salary = 30000

      val factory = Validation.buildDefaultValidatorFactory
      val validator = factory.getValidator

      val constraintViolations =
        validator
          .validate(employee)
          .toArray(Array.empty[ConstraintViolation[Any]])
          .sortWith(_.getPropertyPath.toString < _.getPropertyPath.toString)

      constraintViolations should be(empty)
    }
  }
}
