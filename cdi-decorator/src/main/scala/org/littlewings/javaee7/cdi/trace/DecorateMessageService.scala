package org.littlewings.javaee7.cdi.trace

import javax.annotation.Priority
import javax.decorator.{Decorator, Delegate}
import javax.inject.Inject
import javax.interceptor.Interceptor

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
class DecorateMessageService extends MessageService {
  @Inject
  @Delegate
  private var messageService: MessageService = _

  override def get: String = {
    println("start Decorator.")

    try {
      messageService.get
    } finally {
      println("end Decorator.")
    }
  }
}
