package org.littlewings.javaee7

import scala.io.StdIn

import java.io.File

import org.apache.catalina.startup.Tomcat
import org.apache.tomcat.util.descriptor.web.ContextResource

object TomcatBootsrap {
  def main(args: Array[String]): Unit = {
    val port = 8080
    val tomcat = new Tomcat

    // ポートはデフォルトで8080
    tomcat.setPort(port)

    try {
      // ベースのディレクトリ、DocbaseはSpring Bootを参考に
      tomcat.setBaseDir(createTempDir("tomcat", port).getAbsolutePath)
      val context =
        tomcat.addWebapp("", createTempDir("tomcat-docbase", port).getAbsolutePath)
  
      // CDIでWEB-INF/classesに配置されていなくても対象とされる、「flat」に設定
      context.addParameter("org.jboss.weld.environment.servlet.archive.isolation", "false")
      // RESTEasyとCDIの統合
      context.addParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory")

      // 組み込みTomcatでJNDIを有効に
      tomcat.enableNaming()

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

  def createTempDir(prefix: String, port: Int): File = {
    val tempDir = File.createTempFile(s"${prefix}.", s".${port}")
    tempDir.delete()
    tempDir.mkdir()
    tempDir.deleteOnExit()
    tempDir
  }
}
