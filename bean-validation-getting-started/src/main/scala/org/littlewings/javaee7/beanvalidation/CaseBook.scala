package org.littlewings.javaee7.beanvalidation

import scala.annotation.meta.field

import javax.validation.constraints.{Min, NotNull, Size}

case class CaseBook(
  @(NotNull @field)
  @(Size @field)(min = 14, max = 14)
  isbn: String,

  @(NotNull @field)
  title: String,

  @(Min @field)(1)
  price: Int
)
