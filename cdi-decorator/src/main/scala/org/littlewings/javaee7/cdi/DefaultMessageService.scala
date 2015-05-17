package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DefaultMessageService extends MessageService {
  @AddBrackets
  override def get: String = "Hello World!"

  override def getWith(prefix: String, suffix: String): String = s"${prefix}${get}${suffix}"
}
