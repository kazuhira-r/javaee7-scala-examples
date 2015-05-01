package org.littlewings.javaee7.interceptor

import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
@SerialVersionUID(1L)
class MixedMessageService extends Serializable {
  @Inject
  private var delegate: AccessModifierMessageService = _

  @AddStar
  def joinChain(separator: String, tokens: String*): String =
    delegate.join(separator, tokens: _*)

  @AddStar
  def joinChainToPackagePrivate(separator: String, tokens: String*): String =
    delegate.joinPackagePrivate(separator, tokens: _*)
}
