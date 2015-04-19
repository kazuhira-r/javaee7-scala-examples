package org.littlewings.javaee7

import scala.io.Source

import java.net.URL

import javax.ws.rs.ApplicationPath

import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource

import org.junit.runner.RunWith
import org.junit.Test
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

import org.littlewings.javaee7.rest._

object JaxrsTest extends Deployer

@RunWith(classOf[Arquillian])
@RunAsClient
class JaxrsTest extends JUnitSuite {
  private val resourcePrefix: String =
    classOf[JaxrsApplication]
      .getAnnotation(classOf[ApplicationPath])
      .value

  @ArquillianResource
  private var deploymentUrl: URL = _

  @Test
  def testResourceAdd(): Unit =
    Source
      .fromURL(s"${deploymentUrl}${resourcePrefix}/calc/add?a=1&b=2")
      .mkString should be ("3")

  @Test
  def testResourceMultiply(): Unit =
    Source
      .fromURL(s"${deploymentUrl}${resourcePrefix}/calc/multiply?a=3&b=4")
      .mkString should be ("12")
}
