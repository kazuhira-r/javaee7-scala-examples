package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

@RequestScoped
class CalcService {
  def add(p1: Int, p2: Int): Int =
    p1 + p2

  def multiply(p1: Int, p2: Int): Int =
    p1 * p2
}
