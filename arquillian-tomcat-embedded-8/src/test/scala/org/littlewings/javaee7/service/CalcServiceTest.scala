package org.littlewings.javaee7.service

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

object CalcServiceTest {
  @Deployment
  def createDeployment: WebArchive =
    ShrinkWrap
      .create(classOf[WebArchive])
}

@RunWith(classOf[Arquillian])
class CalcServiceTest extends JUnitSuite {
  @Inject
  private var calcService: CalcService = _

  @Test
  def testAdd(): Unit =
    calcService.add(1, 2) should be(3)
}
