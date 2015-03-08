package org.littlewings.javaee7

import scala.io.StdIn

import java.io.File

import org.apache.catalina.startup.Tomcat
import org.apache.tomcat.util.descriptor.web.ContextResource

object TomcatBootsrap {
  def main(args: Array[String]): Unit = {

    val tomcat = new Tomcat

    // ポートはデフォルトで8080
    // tomcat.setPort(8080)

    try {
      // 組み込みTomcatでJNDIを有効に
      tomcat.enableNaming()

      // ベースのディレクトリ、DocbaseはSpring Bootを参考に
      tomcat.setBaseDir(createTempDir(tomcat, "tomcat").getAbsolutePath)
      val context =
        tomcat.addWebapp("", createTempDir(tomcat, "tomcat-docbase").getAbsolutePath)
  
      // CDIでWEB-INF/classesに配置されていなくても対象とされる、「flat」に設定
      context.addParameter("org.jboss.weld.environment.servlet.archive.isolation", "false")
      // RESTEasyとCDIの統合
      context.addParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory")

      // BeanManaerをJNDIリソースとして定義
      val resource = new ContextResource
      resource.setAuth("Container")
      resource.setName("BeanManager")
      resource.setType("javax.enterprise.inject.spi.BeanManager")
      resource.setProperty("factory", "org.jboss.weld.resources.ManagerObjectFactory")
      context.getNamingResources.addResource(resource)

      // Tomcatの起動
      tomcat.start()

      // Enter打ったら終了
      StdIn.readLine("> Enter stop")
      // 普通、待機はこっち
      // tomcat.getServer.await()

    } finally {
      // Tomcatの破棄と停止
      tomcat.stop()
      tomcat.destroy()
    }
  }

  def createTempDir(tomcat: Tomcat, prefix: String): File = {
    val tempDir = File.createTempFile(s"${prefix}.", s".${tomcat.getConnector.getPort}")
    tempDir.delete()
    tempDir.mkdir()
    tempDir.deleteOnExit()
    tempDir
  }
}
