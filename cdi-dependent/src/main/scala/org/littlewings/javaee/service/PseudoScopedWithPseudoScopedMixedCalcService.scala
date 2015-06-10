package org.littlewings.javaee.service

import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class PseudoScopedWithPseudoScopedMixedCalcService {
  @Inject
  var delegate: PseudoScopedCalcService = _

  def add(a: Int, b: Int): (Int, String) = (delegate.add(a, b)._1, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (delegate.multiply(a, b)._1, getClass.getSimpleName)
}
