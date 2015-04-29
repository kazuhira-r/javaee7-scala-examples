package org.littlewings.javaee.service.interceptor

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@Trace
@ApplicationScoped
@SerialVersionUID(1L)
class NormalScopedMixedCalcService extends Serializable {
  @Inject
  var delegate: PseudoScopedCalcService = _

  def add(a: Int, b: Int): (Int, String) = (delegate.add(a, b)._1, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (delegate.multiply(a, b)._1, getClass.getSimpleName)
}
