package org.littlewings.javaee7.cdi

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
class MessageService {
  def message: String = "Hello MessageService!!"

  def message(prefix: String, suffix: String): String = prefix + message + suffix
}

@RequestScoped
class WrappedService {
  @Inject
  var messageService: MessageService = _

  def message: String = messageService.message

  def message(prefix: String, suffix: String): String = messageService.message(prefix, suffix)
}
