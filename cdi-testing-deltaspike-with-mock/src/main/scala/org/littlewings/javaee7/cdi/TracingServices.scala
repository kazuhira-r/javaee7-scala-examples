package org.littlewings.javaee7.cdi

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
@EnableTracing
class TracingMessageService {
  def message: String = "Hello MessageService!!"

  def message(prefix: String, suffix: String): String = prefix + message + suffix
}

@RequestScoped
@EnableTracing
class TracingWrappedService {
  @Inject
  var messageService: TracingMessageService = _

  def message: String = messageService.message

  def message(prefix: String, suffix: String): String = messageService.message(prefix, suffix)
}
