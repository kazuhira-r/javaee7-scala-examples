package org.littlewings.javaee.service

import javax.enterprise.context.Dependent

@Dependent
class PseudoScopedCalcService {
  def add(a: Int, b: Int): (Int, String) = (a + b, getClass.getSimpleName)

  def multiply(a: Int, b: Int): (Int, String) = (a * b, getClass.getSimpleName)
}
