package org.littlewings.javaee.service.interceptor

import javax.enterprise.context.ApplicationScoped

@Trace
@ApplicationScoped
@SerialVersionUID(1L)
class NormalScopedCalcService extends Serializable {
  def add(a: Int, b: Int): (Int, String) = (a + b, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (a * b, getClass.getSimpleName)
}
