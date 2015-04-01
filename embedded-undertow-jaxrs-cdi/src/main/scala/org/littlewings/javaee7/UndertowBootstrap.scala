package org.littlewings.javaee7

import scala.collection.JavaConverters._
import scala.io.StdIn

import io.undertow.{ Handlers, Undertow }
import io.undertow.server.HttpHandler
import io.undertow.server.handlers.PathHandler
import io.undertow.servlet.Servlets
import io.undertow.servlet.api.ServletContainerInitializerInfo
import io.undertow.servlet.util.DefaultClassIntrospector
import org.jboss.resteasy.plugins.servlet.ResteasyServletInitializer
import org.jboss.weld.environment.servlet.EnhancedListener

import org.littlewings.javaee7.rest._

object UndertowBootstrap {
  def main(args: Array[String]): Unit = {
    val contextPath = ""
    val port = 8080

    val deployment =
      Servlets
        .deployment
        .setClassLoader(getClass.getClassLoader)
        .setContextPath(contextPath)
        .setDeploymentName("jax-rs-with-cdi")
        // .addInitParameter("org.jboss.weld.environment.servlet.archive.isolation", "false")  // なくても動くみたい
        .addInitParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory")
        .addServletContainerInitalizer(new ServletContainerInitializerInfo(classOf[EnhancedListener],
          DefaultClassIntrospector.INSTANCE.createInstanceFactory(classOf[EnhancedListener]),
          null))
        .addServletContainerInitalizer(new ServletContainerInitializerInfo(classOf[ResteasyServletInitializer],
          DefaultClassIntrospector.INSTANCE.createInstanceFactory(classOf[ResteasyServletInitializer]),
          jaxrsClasses))

    val manager = Servlets.defaultContainer.addDeployment(deployment)
    manager.deploy()

    val serverHandler = manager.start()
    val handler: HttpHandler =
      if (contextPath.isEmpty) serverHandler
      else Handlers.path.addPrefixPath(contextPath, serverHandler)

    val server =
      Undertow
        .builder
        .addHttpListener(port, "localhost")
        .setHandler(handler)
        .build

    try {
      // Undertowの起動
      server.start()

      // Enter打ったら終了
      StdIn.readLine("> Enter stop")


    } finally {
      // Undertowの停止
      server.stop()
    }
  }

  private def jaxrsClasses: java.util.Set[Class[_]] =
    Set[Class[_]](
      classOf[JaxrsApplication],
      classOf[CalcResource]
    ).asJava
}
