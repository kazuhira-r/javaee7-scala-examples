package org.littlewings.javaee7.beanvalidation

import javax.validation.{ConstraintValidator, ConstraintValidatorContext}

import org.jboss.logging.Logger

import scala.util.matching.Regex

class AlphabeticalValidator extends ConstraintValidator[Alphabetical, String] {
  override def initialize(constraintAnnotation: Alphabetical): Unit = ()

  override def isValid(value: String, context: ConstraintValidatorContext): Boolean = {
    val logger = Logger.getLogger(getClass)
    logger.infof("Constraint[%s], property[%s]", classOf[Alphabetical].getSimpleName, value.asInstanceOf[Any])

    val regex = """[a-zA-Z]*""".r

    value match {
      case null => true
      case regex(_) => true
      case _ =>
        context.disableDefaultConstraintViolation()
        context
          .buildConstraintViolationWithTemplate("アルファベットで入力してください")
          .addConstraintViolation()
        false
    }
  }
}
