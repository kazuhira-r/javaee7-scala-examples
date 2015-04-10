package org.littlewings.javaee7.extension

import javax.enterprise.inject.spi.CDI

import org.jboss.weld.environment.se.Weld

import org.littlewings.javaee7.entity._
import org.littlewings.javaee7.entity.sub._
import org.littlewings.javaee7.rest._
import org.littlewings.javaee7.rest.sub._
import org.littlewings.javaee7.service._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CdiExtensionSpec extends FunSpec {
  describe("CDI Extension Spec") {
    it("Simple CDI Extension case") {
      val weld = new Weld
      val container = weld.initialize()

      // Exension自身をCDIで取得できる
      val myExtension = CDI.current.select(classOf[MyExtension]).get

      myExtension.classes should contain allOf (
        classOf[TopResource],
        classOf[SubResource],
        classOf[TopEntity],
        classOf[SubEntity],
        classOf[ExtensionService],
        classOf[MyExtension]  // Extension自身が入っている！
      )

      // 他の管理BeanにExtensionをインジェクション可能
      val extensionService = CDI.current.select(classOf[ExtensionService]).get
      extensionService.extensionClasses should contain allOf(
        classOf[TopResource],
        classOf[SubResource],
        classOf[TopEntity],
        classOf[SubEntity],
        classOf[ExtensionService],
        classOf[MyExtension]
      )

      weld.shutdown()
    }

    it("Annotation Type Spec CDI Extension case") {
      val weld = new Weld
      val container = weld.initialize()

      val entityExtension = CDI.current.select(classOf[EntityExtension]).get

      entityExtension.entityClasses should contain allOf(
        classOf[TopEntity],
        classOf[SubEntity]
      )

      entityExtension.entityClasses should contain noneOf(
        classOf[TopResource],
        classOf[SubResource],
        classOf[ExtensionService],
        classOf[MyExtension],
        classOf[EntityExtension]
      )

      weld.shutdown()
    }

    it("Annotation Method Spec CDI Extension case") {
      val weld = new Weld
      val container = weld.initialize()

      val postExtension = CDI.current.select(classOf[PostExtension]).get

      postExtension.postClasses should contain (
        classOf[SubResource]
      )

      postExtension.postClasses should contain noneOf(
        classOf[TopEntity],
        classOf[SubEntity],
        classOf[TopResource],
        classOf[ExtensionService],
        classOf[MyExtension],
        classOf[EntityExtension],
        classOf[PostExtension]
      )

      weld.shutdown()
    }
  }
}
