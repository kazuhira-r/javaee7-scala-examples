package org.littlewings.javaee7.config

import javax.inject.Inject

import org.apache.deltaspike.core.api.config.ConfigResolver
import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class ConfigResolverSpec extends JUnitSuite with Matchers {
  @Test
  def configResolver(): Unit = {
    ConfigResolver.getPropertyValue("database.server") should be("localhost")
    ConfigResolver.getPropertyValue("database.port") should be("3306")
    ConfigResolver.getPropertyValue("database.name") should be("test")

    ConfigResolver.getPropertyValue("missing.key") should be(null)
    ConfigResolver.getPropertyValue("missing.key", "defaultValue") should be("defaultValue")

    ConfigResolver.getPropertyValue("jdbc.url") should be("jdbc:mysql://localhost:3306/test")
  }

  @Inject
  var projectStage: ProjectStage = _

  @Before
  def setUp(): Unit = {
    val configResolverProjectStageField = classOf[ConfigResolver].getDeclaredField("projectStage")
    configResolverProjectStageField.setAccessible(true)
    configResolverProjectStageField.set(null, projectStage)
  }

  @Test
  def configResolverProjectStageAware(): Unit = {
    ConfigResolver.getProjectStageAwarePropertyValue("database.server") should be("ut-server")
    ConfigResolver.getProjectStageAwarePropertyValue("database.port") should be("13306")
    ConfigResolver.getProjectStageAwarePropertyValue("database.name") should be("ut-test")

    ConfigResolver.getProjectStageAwarePropertyValue("jdbc.url") should be("jdbc:mysql://ut-server:13306/ut-test")
    // ConfigResolver.getProjectStageAwarePropertyValue("jdbc.url") should be("jdbc:mysql://localhost:3306/test")
  }

  @Test
  def typedResolver(): Unit = {
    ConfigResolver
      .resolve("database.server")
      .as(classOf[String])
      .getValue should be("ut-server")

    ConfigResolver
      .resolve("database.port")
      .as(classOf[Integer])
      .getValue should be(13306)

    ConfigResolver
      .resolve("missing.key")
      .as(classOf[String])
      .getValue should be(null)

    ConfigResolver
      .resolve("missing.key")
      .as(classOf[String])
      .withDefault("defaultValue")
      .getValue should be("defaultValue")

    ConfigResolver
      .resolve("jdbc.url")
      .as(classOf[String])
      .getValue should be("jdbc:mysql://${database.server}:${database.port}/${database.name}")

    ConfigResolver
      .resolve("jdbc.url")
      .as(classOf[String])
      .evaluateVariables(true)
      .getValue should be("jdbc:mysql://localhost:3306/test")
  }
}
