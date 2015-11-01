package org.littlewings.javaee7.rest

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer
import org.jboss.resteasy.spi.ResteasyDeployment
import org.scalatest.FunSpec
import org.scalatest.Matchers._

import scala.io.Source

class RestEasyNetty3Spec extends FunSpec {
  describe("RestEasyNetty3Spec") {
    it("calc/add, using Application") {
      val netty = new NettyJaxrsServer
      val deployment = netty.getDeployment
      // 以下も可
      // val deployment = new ResteasyDeployment
      deployment.setApplicationClass(classOf[JaxrsApplication].getName)
      netty.setRootResourcePath("")
      netty.setPort(8080)
      netty.setDeployment(deployment)
      netty.start()

      val source = Source.fromURL("http://localhost:8080/calc/add?a=5&b=3")
      source.mkString.toInt should be(8)
      source.close()

      netty.stop()
    }

    it("calc/add, Resource only") {
      val netty = new NettyJaxrsServer
      val deployment = netty.getDeployment
      netty.setRootResourcePath("")
      netty.setPort(8080)
      netty.setDeployment(deployment)
      netty.start()
      deployment.getRegistry.addPerRequestResource(classOf[CalcResource])

      val source = Source.fromURL("http://localhost:8080/calc/add?a=5&b=3")
      source.mkString.toInt should be(8)
      source.close()

      netty.stop()
    }
  }
}
