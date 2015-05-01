package org.littlewings.javaee7.interceptor

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
@SerialVersionUID(1L)
class NormalScopedMessageService extends Serializable {
  @AddStar
  def join(separator: String, tokens: String*): String =
    tokens.mkString(separator)

  @AddStar
  def joinChain(separator: String, tokens: String*): String = join(separator, tokens: _*)
}
