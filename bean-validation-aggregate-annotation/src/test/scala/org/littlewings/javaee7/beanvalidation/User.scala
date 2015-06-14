package org.littlewings.javaee7.beanvalidation

import javax.validation.constraints.NotNull

class User {
  @NotNull
  @UserId
  var id: String = _

  @NotNull
  @UserIdAggregate
  var idValidAggregate: String = _
}
