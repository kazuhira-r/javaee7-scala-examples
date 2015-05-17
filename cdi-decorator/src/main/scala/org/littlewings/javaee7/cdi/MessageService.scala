package org.littlewings.javaee7.cdi

trait MessageService {
  def get: String

  def getWith(prefix: String, suffix: String): String
}
