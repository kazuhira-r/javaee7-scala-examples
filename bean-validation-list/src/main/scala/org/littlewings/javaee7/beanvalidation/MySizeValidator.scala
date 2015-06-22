package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.jboss.logging.Logger

class MySizeValidator extends ConstraintValidator[MySize, String] {
  var min: Int = _
  var max: Int = _

  override def initialize(constraintAnnotation: MySize): Unit = {
    min = constraintAnnotation.min
    max = constraintAnnotation.max
  }

  override def isValid(value: String, context: ConstraintValidatorContext): Boolean = {
    val logger = Logger.getLogger(getClass)
    logger.infof("Constraint[%s], property[%s]", classOf[MySize].getSimpleName, value.asInstanceOf[Any])

    value != null && value.size >= min && value.size <= max
  }
}
