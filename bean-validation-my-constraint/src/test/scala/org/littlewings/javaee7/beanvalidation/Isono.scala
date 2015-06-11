package org.littlewings.javaee7.beanvalidation

import javax.validation.constraints.{NotNull, Size}

class Isono {
  @NotNull
  @Size(min = 1, max = 3)
  @Select(Array("カツオ", "ワカメ", "波平", "フネ"))
  var name: String = _
}
