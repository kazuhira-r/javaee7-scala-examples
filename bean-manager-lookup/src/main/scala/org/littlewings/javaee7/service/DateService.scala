package org.littlewings.javaee7.service

import java.time.LocalDateTime

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DateService {
  private[this] val thisTime: LocalDateTime = LocalDateTime.now

  def time: LocalDateTime =
    thisTime
}
