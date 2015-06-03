package org.littlewings.javaee7.cdi

import javax.enterprise.context.Dependent
import javax.enterprise.event.Observes

@Dependent
class LoginObserver {
  def observe(@Observes event: LoginEvent): Unit =
    println(s"${event.lastName + event.firstName}さんがログインしました！！")
}

@Dependent
class LoginObserver2 {
  def observe(@Observes event: LoginEvent): Unit =
    println(s"${event.lastName + event.firstName}さんがログインしたらしいですよ？")
}
