package org.littlewings.javaee7.cdi.service

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Alternative

import org.littlewings.javaee7.cdi.stereotype.Another;

trait MessageService {
  def get: String
}

//@Alternative
@RequestScoped
class HelloWorldMessageService extends MessageService {
  override def get: String = "Hello World"
}

@Another
//@Alternative
@RequestScoped
class AnotherMessageService extends MessageService {
  override def get: String = "Another Message Service"
}
