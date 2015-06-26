package org.littlewings.javaee7.beanvalidation

import java.util.Locale
import javax.validation.{MessageInterpolator, Validation}

class MyCustomMessageInterpolator(delegate: MessageInterpolator) extends MessageInterpolator {
  def this() = this(Validation.byDefaultProvider.configure.getDefaultMessageInterpolator)

  override def interpolate(messageTemplate: String, context: MessageInterpolator.Context): String = {
    println(s"received messageTemplate = $messageTemplate")
    delegate.interpolate(messageTemplate, context)
  }

  override def interpolate(messageTemplate: String, context: MessageInterpolator.Context, locale: Locale): String = {
    println(s"received messageTemplate = $messageTemplate")
    delegate.interpolate(messageTemplate, context, locale)
  }
}
