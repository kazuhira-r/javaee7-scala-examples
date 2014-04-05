package org.littlewings.javaee7.beanvalidation

import scala.annotation.meta.field

import javax.validation.constraints.{Digits, Size}

case class Bean(
  @(Digits @field)(integer = 2, fraction = 0)
  @(Size @field)(min = 1000, max = 9999)
  value: String
)
