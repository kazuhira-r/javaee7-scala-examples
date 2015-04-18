package org.littlewings.javaee7.cdi

import java.io.File

import org.apache.catalina.startup.Tomcat

import org.scalatest.Suite
import org.scalatest.BeforeAndAfterAll

trait EmbeddedTomcatCdiSupport extends Suite with BeforeAndAfterAll {
  protected val port: Int = 8080
  protected val tomcat: Tomcat = new Tomcat

  override def beforeAll(): Unit = {
    tomcat.setPort(port)

    tomcat.setBaseDir(createTempDir("tomcat", port).getAbsolutePath)

    val context =
      tomcat.addWebapp("", createTempDir("tomcat-docbase", port).getAbsolutePath)

    context.addParameter("org.jboss.weld.environment.servlet.archive.isolation", "false")
    context.addParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory")

    tomcat.start()
  }

  override def afterAll(): Unit = {
    tomcat.stop()
    tomcat.destroy()
  }

  protected def createTempDir(prefix: String, port: Int): File = {
    val tempDir = File.createTempFile(s"${prefix}.", s".${port}")
    tempDir.delete()
    tempDir.mkdir()
    tempDir.deleteOnExit()
    tempDir
  }
}
