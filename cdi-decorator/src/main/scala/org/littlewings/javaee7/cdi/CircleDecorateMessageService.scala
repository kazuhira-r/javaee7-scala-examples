package org.littlewings.javaee7.cdi

import javax.annotation.Priority
import javax.decorator.{Delegate, Decorator}
import javax.inject.Inject
import javax.interceptor.Interceptor

@Decorator
@Priority(Interceptor.Priority.APPLICATION + 10)
class CircleDecorateMessageService extends MessageService {
  @Inject
  @Delegate
  private var messageService: MessageService = _

  override def get: String = s"◯${messageService.get}◯"

  override def getWith(prefix: String, suffix: String): String = s"${prefix}${get}${suffix}"
}
