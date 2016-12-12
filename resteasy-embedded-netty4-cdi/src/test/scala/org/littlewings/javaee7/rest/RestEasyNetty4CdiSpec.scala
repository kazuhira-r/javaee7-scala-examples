package org.littlewings.javaee7.rest

import javax.enterprise.inject.spi.CDI

import org.jboss.resteasy.cdi.{CdiInjectorFactory, ResteasyCdiExtension}
import org.jboss.resteasy.plugins.server.netty.cdi.CdiNettyJaxrsServer
import org.jboss.resteasy.spi.ResteasyDeployment
import org.jboss.weld.environment.se.Weld
import org.scalatest.FunSpec
import org.scalatest.Matchers._

import scala.io.Source

class RestEasyNetty4CdiSpec extends FunSpec {
  describe("RestEasyNetty4CdiSpec") {
    it("calc/add, using Application") {
      val weld = new Weld
      weld.initialize()

      val cdiExtension = CDI.current().select(classOf[ResteasyCdiExtension]).get

      val netty = new CdiNettyJaxrsServer
      val deployment = netty.getDeployment
      // 以下も可
      // val deployment = new ResteasyDeployment
      deployment.setActualResourceClasses(cdiExtension.getResources)
      deployment.setInjectorFactoryClass(classOf[CdiInjectorFactory].getName)
      deployment.setActualProviderClasses(cdiExtension.getProviders)
      netty.setRootResourcePath("")
      netty.setPort(8080)
      netty.setDeployment(deployment)
      netty.start()

      val source = Source.fromURL("http://localhost:8080/calc/add?a=5&b=3")
      source.mkString.toInt should be(8)
      source.close()

      netty.stop()

      weld.shutdown()
    }

    it("calc/add, Resource only") {
      val weld = new Weld
      weld.initialize()

      val cdiExtension = CDI.current().select(classOf[ResteasyCdiExtension]).get

      val netty = new CdiNettyJaxrsServer
      val deployment = netty.getDeployment
      deployment.setActualResourceClasses(cdiExtension.getResources)
      deployment.setInjectorFactoryClass(classOf[CdiInjectorFactory].getName)
      netty.setRootResourcePath("")
      netty.setPort(8080)
      netty.setDeployment(deployment)
      netty.start()

      val source = Source.fromURL("http://localhost:8080/calc/add?a=5&b=3")
      source.mkString.toInt should be(8)
      source.close()

      netty.stop()

      weld.shutdown()
    }
  }
}
