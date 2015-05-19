package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

@RequestScoped
class DefaultMessageService extends MessageService {
  override def get: String = "Hello World"
}
