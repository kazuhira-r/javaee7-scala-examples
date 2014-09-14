package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

@RequestScoped
class CalcService {
  def add(left: Int, right: Int): Int =
    left + right

  def multiply(left: Int, right: Int): Int =
    left * right
}
