package org.littlewings.javaee7.validation.rest

import scala.beans.BeanProperty

import javax.validation.Valid
import javax.validation.constraints.{Digits, NotNull, Size}

class Param {
  @NotNull
  @BeanProperty
  var name: String = _

  @Digits(integer = 3, fraction = 0)
  @BeanProperty
  var num: String = _

  @Valid
  @Size(min = 1)
  @BeanProperty
  var subs: java.util.List[Sub] = _
}

class Sub {
  @NotNull
  @BeanProperty
  var value: String = _
}
