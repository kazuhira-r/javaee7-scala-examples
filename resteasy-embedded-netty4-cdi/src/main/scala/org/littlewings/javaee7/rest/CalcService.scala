package org.littlewings.javaee7.rest

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CalcService {
  def add(a: Int, b: Int): Int = a + b
}
