package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageService {
  @PrintStar
  @PrintCircle
  def print(): Unit = println("Hello World")
}
