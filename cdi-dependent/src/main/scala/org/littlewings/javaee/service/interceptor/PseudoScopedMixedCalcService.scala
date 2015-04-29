package org.littlewings.javaee.service.interceptor

import javax.enterprise.context.Dependent
import javax.inject.Inject

@Trace
@Dependent
@SerialVersionUID(1L)
class PseudoScopedMixedCalcService extends Serializable {
  @Inject
  var delegate: NormalScopedCalcService = _

  def add(a: Int, b: Int): (Int, String) = (delegate.add(a, b)._1, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (delegate.multiply(a, b)._1, getClass.getSimpleName)
}
