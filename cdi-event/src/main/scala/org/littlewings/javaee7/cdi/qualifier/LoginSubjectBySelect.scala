package org.littlewings.javaee7.cdi.qualifier

import javax.enterprise.context.Dependent
import javax.enterprise.event.Event
import javax.enterprise.util.AnnotationLiteral
import javax.inject.Inject

@Dependent
class LoginSubjectBySelect {
  @Inject
  private var loginEvent: Event[LoginEvent] = _

  def login(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    loginEvent.fire(event)
  }

  def loginAsUser(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    loginEvent.select(new AnnotationLiteral[User] {}).fire(event)
  }

  def loginAsAdmin(firstName: String, lastName: String): Unit = {
    val event = new LoginEvent(firstName, lastName)
    loginEvent.select(classOf[LoginEvent], new AnnotationLiteral[Admin] {}).fire(event)
  }
}
