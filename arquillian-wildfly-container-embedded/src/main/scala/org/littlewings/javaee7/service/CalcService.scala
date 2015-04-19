package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

@RequestScoped
class CalcService {
  def add(a: Int, b: Int): Int = a + b

  def multiply(a: Int, b: Int): Int = a * b
}
