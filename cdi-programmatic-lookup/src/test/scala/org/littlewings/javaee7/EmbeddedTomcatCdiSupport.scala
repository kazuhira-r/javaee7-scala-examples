package org.littlewings.javaee7

import java.io.File

import org.apache.catalina.startup.Tomcat
import org.apache.tomcat.util.descriptor.web.ContextResource
import org.scalatest.{BeforeAndAfterAll, Suite}

trait EmbeddedTomcatCdiSupport extends Suite with BeforeAndAfterAll {
  protected val port: Int = 8080
  protected val tomcat: Tomcat = new Tomcat
  protected val baseDir: File = createTempDir("tomcat", port)
  protected val docBaseDir: File = createTempDir("tomcat-docbase", port)

  override def beforeAll(): Unit = {
    tomcat.setPort(port)

    tomcat.setBaseDir(baseDir.getAbsolutePath)

    val context =
      tomcat.addWebapp("", docBaseDir.getAbsolutePath)

    context.addParameter("org.jboss.weld.environment.servlet.archive.isolation", "false")
    context.addParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory")

    tomcat.enableNaming()

    val resource = new ContextResource
    resource.setAuth("Container")
    resource.setName("BeanManager")
    resource.setType("javax.enterprise.inject.spi.BeanManager")
    resource.setProperty("factory", "org.jboss.weld.resources.ManagerObjectFactory")
    context.getNamingResources.addResource(resource)

    tomcat.start()
  }

  override def afterAll(): Unit = {
    tomcat.stop()
    tomcat.destroy()

    deleteDirs(baseDir)
    deleteDirs(docBaseDir)
  }

  private def createTempDir(prefix: String, port: Int): File = {
    val tempDir = File.createTempFile(s"${prefix}.", s".${port}")
    tempDir.delete()
    tempDir.mkdir()
    tempDir.deleteOnExit()
    tempDir
  }

  private def deleteDirs(file: File): Unit = {
    file
      .listFiles
      .withFilter(f => f.getName != "." && f.getName != "..")
      .foreach {
      case d if d.isDirectory => deleteDirs(d)
      case f => f.delete()
    }

    file.delete()
  }
}
