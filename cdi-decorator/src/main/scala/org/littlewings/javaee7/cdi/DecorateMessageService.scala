package org.littlewings.javaee7.cdi

import javax.annotation.Priority
import javax.decorator.{Decorator, Delegate}
import javax.inject.Inject
import javax.interceptor.Interceptor

@Decorator
@Priority(Interceptor.Priority.APPLICATION + 100)
class DecorateMessageService extends MessageService {
  @Inject
  @Delegate
  private var messageService: MessageService = _

  // @AddBrackets  // <-効かない
  override def get: String = s"★${messageService.get}★"

  // この呼び出しは、DefaultMessageService#getWithにInterceptorが紐付けられていない
  // また、Decorator内からInterceptorが紐付けられたメソッドを呼び出しても、Interceptorはかからない模様
  // （Interceptorを適用したメソッド内で、別のInterceptorを適用したメソッドを呼び出しても効かないのと同じ）
  override def getWith(prefix: String, suffix: String): String = s"${prefix}${get}${suffix}"
}
