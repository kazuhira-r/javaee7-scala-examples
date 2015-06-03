package org.littlewings.javaee7.cdi

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
}
