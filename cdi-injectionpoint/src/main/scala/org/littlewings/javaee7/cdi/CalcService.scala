package org.littlewings.javaee7.cdi

import javax.enterprise.context.{ApplicationScoped, Dependent}
import javax.inject.Inject

import org.jboss.logging.Logger

trait CalcService {
  def add(a: Int, b: Int): Int
}

@Dependent
class SimpleCalcService extends CalcService {
  @Inject
  private var logger: Logger = _

  override def add(a: Int, b: Int): Int = {
    val result = a + b
    logger.infof("%d + %d = %d", a, b, result)
    result
  }
}

@MyStereotype
@ServiceQualifier
@ApplicationScoped
class WithQualifierStereotypeCalcService extends CalcService {
  @LoggerQualifier
  @Inject
  private var logger: Logger = _

  override def add(a: Int, b: Int): Int = {
    val result = a + b
    logger.infof("%d + %d = %d", a, b, result)
    result
  }
}
