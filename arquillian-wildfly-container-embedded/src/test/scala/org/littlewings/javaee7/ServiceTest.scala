package org.littlewings.javaee7

import javax.inject.Inject

import org.jboss.arquillian.junit.Arquillian

import org.junit.runner.RunWith
import org.junit.Test
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

import org.littlewings.javaee7.service._

object ServiceTest extends Deployer

@RunWith(classOf[Arquillian])
class ServiceTest extends JUnitSuite {
  @Inject
  private var calcService: CalcService = _

  @Test
  def testServiceAdd(): Unit =
    calcService.add(1, 2) should be (3)

  @Test
  def testServiceMultiply(): Unit =
    calcService.multiply(3, 4) should be (12)
}
