package org.littlewings.javaee7.cdi.qualifier

import javax.enterprise.context.Dependent
import javax.enterprise.event.Event
import javax.inject.Inject

@Dependent
class LoginSubject {
  @Inject
  private var loginEvent: Event[LoginEvent] = _

  def login(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    loginEvent.fire(event)
  }

  @User
  @Inject
  private var userLoginEvent: Event[LoginEvent] = _

  def loginAsUser(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    userLoginEvent.fire(event)
  }

  @Admin
  @Inject
  private var adminLoginEvent: Event[LoginEvent] = _

  def loginAsAdmin(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    adminLoginEvent.fire(event)
  }
}
