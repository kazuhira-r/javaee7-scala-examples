package org.littlewings.javaee7.microprofile.service

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.wildfly.swarm.Swarm
import org.wildfly.swarm.arquillian.CreateSwarm

object CalcServiceTest {
  @Deployment
  def createDeployment: WebArchive =
    ShrinkWrap
      .create(classOf[WebArchive])
      .addClass(classOf[CalcService])
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
class CalcServiceTest extends Matchers {
  @Inject
  var calcService: CalcService = _

  @Test
  def addTest(): Unit =
    calcService.add(5, 3) should be(8)
}
