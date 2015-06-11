package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

class SelectValidator extends ConstraintValidator[Select, String] {
  private var selectableValues: Array[String] = _

  override def initialize(constraintAnnotation: Select): Unit =
    selectableValues = constraintAnnotation.value()

  override def isValid(value: String, context: ConstraintValidatorContext): Boolean =
    value match {
      case null => true
      case _ => selectableValues.exists(_ == value)
    }
}
