package org.littlewings.javaee7.rest

import java.net.URL

import javax.ws.rs.ApplicationPath
import javax.ws.rs.client.ClientBuilder

import org.jboss.arquillian.container.test.api.{Deployment, RunAsClient}
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven

import org.littlewings.javaee7.service.CalcService

import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

import org.junit.Test
import org.junit.runner.RunWith

object HelloResourceTest {
  @Deployment
  def createDeployment: WebArchive =
   ShrinkWrap
     .create(classOf[WebArchive], "arquillian-test.war")
     .addPackages(true, classOf[HelloResource].getPackage.getName)
     .addClass(classOf[CalcService])
     .addAsLibraries {
       Maven
         .resolver
         .loadPomFromFile("pom.xml")
         .importRuntimeDependencies
         .resolve("org.scalatest:scalatest_2.11:2.2.1")
         .withTransitivity
         .asFile: _*
     }
}

@RunWith(classOf[Arquillian])
@RunAsClient
class HelloResourceTest extends JUnitSuite {
  private val resourcePrefix: String =
    classOf[JaxrsApplication]
      .getAnnotation(classOf[ApplicationPath])
      .value

  @ArquillianResource
  private var deploymentUrl: URL = _

  @Test
  def helloWorld1Test(): Unit = {
    val client = ClientBuilder.newBuilder.build

    try {
      val response =
        client
          .target(s"${deploymentUrl}${resourcePrefix}/hello/index")
          .request
          .get

      response.readEntity(classOf[String]) should be ("Hello World Hello World")

      response.close()
    } finally {
      client.close()
    }
  }

  @Test
  def helloWorld2Test(): Unit = {
    val client = ClientBuilder.newBuilder.build

    try {
      val response =
        client
          .target(s"${deploymentUrl}${resourcePrefix}/hello/index")
          .request
          .get

      response.readEntity(classOf[String]) should be ("Hello World")

      response.close()
    } finally {
      client.close()
    }
  }

  @Test
  def addTest(): Unit = {
    val client = ClientBuilder.newBuilder.build

    try {
      val response =
        client
          .target(s"${deploymentUrl}${resourcePrefix}/hello/add?p1=1&p2=3")
          .request
          .get

      response.readEntity(classOf[String]) should be ("4")

      response.close()
    } finally {
      client.close()
    }
  }
}
