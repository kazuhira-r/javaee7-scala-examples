package org.littlewings.javaee7.config

import javax.inject.Inject

import org.apache.deltaspike.core.api.config.ConfigResolver
import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class DeltaSpikeConfigSpec extends JUnitSuite with Matchers {
  @Inject
  var config: DeltaSpikeConfig = _

  @Inject
  var projectStage: ProjectStage = _

  @Before
  def setUp(): Unit = {
    val configResolverProjectStageField = classOf[ConfigResolver].getDeclaredField("projectStage")
    configResolverProjectStageField.setAccessible(true)
    configResolverProjectStageField.set(null, projectStage)
  }

  @Test
  def configTest(): Unit = {
    config.databaseServer should be("ut-server")
    config.databasePort should be(13306)
    config.databaseName should be("ut-test")
    config.missingKey should be("defaultValue")
    config.missingKeyWithoutDefaultValue should be(null)
    config.jdbcUrl should be("jdbc:mysql://localhost:3306/test")
  }
}
