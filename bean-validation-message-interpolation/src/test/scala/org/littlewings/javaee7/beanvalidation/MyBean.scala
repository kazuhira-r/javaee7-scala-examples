package org.littlewings.javaee7.beanvalidation

import javax.validation.constraints.{Min, Pattern, Size}

class MyBean {
  @Size(min = 3, max = 5)
  var a: String = _

  @Min(5)
  var b: Int = _

  @Pattern(regexp = "^[a-z]$")
  var c: String = _
}

class MyBeanWithMessage {
  @Size(min = 3, max = 5, message = "size must be between {min} and {max}, input = ${validatedValue}")
  var a: String = _

  @Min(value = 5,
    message = "must be greater than or equal to {value}," +
      " formatted input = ${formatter.format('%1$05d', validatedValue)}")
  var b: Int = _

  @Pattern(regexp = "^[a-z]$", message = "must match \"{regexp}\", input = ${validatedValue}")
  var c: String = _
}
