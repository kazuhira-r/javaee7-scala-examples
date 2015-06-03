package org.littlewings.javaee7.cdi.qualifier

import javax.enterprise.context.Dependent
import javax.enterprise.event.Observes

@Dependent
class LoginObserver {
  def loggined(@Observes event: LoginEvent): Unit =
    println(s"${event.lastName + event.firstName}さんがログインしました！！")
}

@Dependent
class AdminLoginObserver {
  def observe(@Observes @Admin event: LoginEvent): Unit =
    println(s"${event.lastName + event.firstName}さんが、管理者としてログインしました")
}

@Dependent
class UserLoginObserver {
  def observe(@Observes @User event: LoginEvent): Unit =
    println(s"${event.lastName + event.firstName}さんが、一般ユーザーとしてログインしました")
}
