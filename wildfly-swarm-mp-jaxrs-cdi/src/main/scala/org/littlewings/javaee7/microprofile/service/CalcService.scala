package org.littlewings.javaee7.microprofile.service

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CalcService {
  def add(a: Int, b: Int): Int = a + b
}
