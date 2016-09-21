package org.littlewings.javaee7.microprofile.rest

import java.net.URL
import javax.ws.rs.ApplicationPath
import javax.ws.rs.client.ClientBuilder

import org.jboss.arquillian.container.test.api.{Deployment, RunAsClient}
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.resolver.api.maven.Maven
import org.junit.Test
import org.junit.runner.RunWith
import org.littlewings.javaee7.microprofile.service.CalcService
import org.scalatest.Matchers
import org.wildfly.swarm.Swarm
import org.wildfly.swarm.arquillian.CreateSwarm
import org.wildfly.swarm.jaxrs.JAXRSArchive

object CalcResourceTest {
  @Deployment
  def createDeployment: JAXRSArchive =
    ShrinkWrap
      .create(classOf[JAXRSArchive])
      .addClasses(classOf[JaxrsActivator], classOf[CalcResource], classOf[CalcService])
      .addAsLibraries(
        Maven
          .resolver
          .loadPomFromFile("pom.xml")
          .importRuntimeDependencies
          .resolve("org.scalatest:scalatest_2.11:3.0.0")
          .withTransitivity
          .asFile: _*
      )

  @CreateSwarm
  def newContainer: Swarm = new Swarm
}

@RunWith(classOf[Arquillian])
@RunAsClient
class CalcResourceTest extends Matchers {
  private val resourcePrefix: String =
    classOf[JaxrsActivator]
      .getAnnotation(classOf[ApplicationPath])
      .value

  @ArquillianResource
  private var deploymentUrl: URL = _

  @Test
  def calcTest(): Unit = {
    val client = ClientBuilder.newBuilder.build

    try {
      val response =
        client
          .target(s"${deploymentUrl}${resourcePrefix}/calc/add?a=5&b=3")
          .request
          .get

      response.readEntity(classOf[String]) should be("8")

      response.close()
    } finally {
      client.close()
    }
  }
}
