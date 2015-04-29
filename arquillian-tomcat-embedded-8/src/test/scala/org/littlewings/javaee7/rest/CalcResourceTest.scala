package org.littlewings.javaee7.rest

import java.io.File
import java.net.URL
import javax.ws.rs.ApplicationPath

import org.jboss.arquillian.container.test.api.{Deployment, RunAsClient}
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

import scala.io.Source

object CalcResourceTest {
  @Deployment
  def createDeployment: WebArchive =
    ShrinkWrap
      .create(classOf[WebArchive])
      .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"))
}

@RunWith(classOf[Arquillian])
@RunAsClient
class CalcResourceTest extends JUnitSuite {
  private val resourcePrefix: String =
    classOf[JaxrsApplication]
      .getAnnotation(classOf[ApplicationPath])
      .value

  @ArquillianResource
  private var url: URL = _

  @Test
  def testAdd(): Unit =
    Source
      .fromURL(s"${url}${resourcePrefix}/calc/add?a=1&b=2")
      .mkString should be("3")
}
