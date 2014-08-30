package org.littlewings.javaee7.service

import javax.inject.Inject

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven

import org.scalatest.Matchers._
import org.scalatest.junit.JUnitSuite

import org.junit.Test
import org.junit.runner.RunWith

object CalcServiceTest {
  @Deployment
  def createDeployment: WebArchive =
   ShrinkWrap
     .create(classOf[WebArchive], "arquillian-test.war")
     .addPackages(true, classOf[CalcService].getPackage.getName)
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
class CalcServiceTest extends JUnitSuite {
  @Inject
  private var calcService: CalcService = _

  @Test
  def add1Test(): Unit =
    calcService.add(1, 2) should be (3)

  @Test
  def add2Test(): Unit =
    calcService.add(2, 3) should be (5)

  @Test
  def add3Test(): Unit =
    calcService.add(1, 1) should be (3)

  @Test
  def multiply1Test(): Unit =
    calcService.multiply(1, 3) should be (3)

  @Test
  def multiply2Test(): Unit =
    calcService.multiply(3, 3) should be (9)
}
