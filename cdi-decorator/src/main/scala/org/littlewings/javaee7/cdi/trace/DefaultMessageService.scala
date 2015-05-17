package org.littlewings.javaee7.cdi.trace

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DefaultMessageService extends MessageService {
  @Trace
  override def get: String = {
    println("called.")
    "Hello World!"
  }
}
