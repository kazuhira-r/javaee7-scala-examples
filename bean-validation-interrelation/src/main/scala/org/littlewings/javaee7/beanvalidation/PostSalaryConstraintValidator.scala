package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

class PostSalaryConstraintValidator extends ConstraintValidator[PostSalaryConstraint, AnyRef] {
  private var postField: String = _
  private var salaryField: String = _

  override def initialize(constraintAnnotation: PostSalaryConstraint): Unit = {
    postField = constraintAnnotation.postField
    salaryField = constraintAnnotation.salaryField
  }

  override def isValid(value: AnyRef, context: ConstraintValidatorContext): Boolean =
    value match {
      case null => true
      case employee =>
        val salary = getFieldValue[Number](employee, salaryField).longValue()

        getFieldValue[String](employee, postField) match {
          case "課長" if salary >= 50000 => true
          case "課長" =>
            context.disableDefaultConstraintViolation()
            context
              .buildConstraintViolationWithTemplate("課長の給料は、50000以上でなければなりません")
              .addPropertyNode(salaryField)
              .addConstraintViolation()
            false
          case "部長" if salary >= 100000 => true
          case "部長" =>
            context.disableDefaultConstraintViolation()
            context
              .buildConstraintViolationWithTemplate("部長の給料は、100000以上でなければなりません")
              .addPropertyNode(postField)
              .addPropertyNode(salaryField)
              .addConstraintViolation()
            false
          case _ if salary < 50000 => true
          case _ =>
            context.disableDefaultConstraintViolation()
            context
              .buildConstraintViolationWithTemplate("平社員の給料は、50000より低くなければなりません")
              .addPropertyNode(salaryField)
              .addConstraintViolation()
            false
        }
    }

  private def getFieldValue[T](owner: AnyRef, fieldName: String): T = {
    val field = owner.getClass.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.get(owner).asInstanceOf[T]
  }
}
