package org.littlewings.javaee.service.interceptor

import javax.enterprise.context.Dependent

@Trace
@Dependent
@SerialVersionUID(1L)
class PseudoScopedCalcService extends Serializable {
  def add(a: Int, b: Int): (Int, String) = (a + b, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (a * b, getClass.getSimpleName)
}
