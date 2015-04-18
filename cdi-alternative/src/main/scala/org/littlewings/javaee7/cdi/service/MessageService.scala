package org.littlewings.javaee7.cdi.service

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Alternative

trait MessageService {
  def get: String
}

@Alternative
@RequestScoped
class HelloWorldMessageService extends MessageService {
  override def get: String = "Hello World"
}

@Alternative
@RequestScoped
class AnotherMessageService extends MessageService {
  override def get: String = "Another Message Service"
}
