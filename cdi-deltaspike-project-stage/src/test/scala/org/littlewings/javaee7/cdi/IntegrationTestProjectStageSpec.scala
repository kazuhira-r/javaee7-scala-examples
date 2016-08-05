package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.apache.deltaspike.testcontrol.api.TestControl
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
@TestControl(projectStage = classOf[ProjectStage.IntegrationTest])
class IntegrationTestProjectStageSpec extends JUnitSuite with Matchers {
  @Inject
  var messageService: MessageService = _

  @Test
  def messageTest(): Unit = {
    messageService.get should be("IntegrationTestStage!!")
  }

  @Inject
  var projectStage: ProjectStage = _

  @Test
  def projectStageTest(): Unit = {
    projectStage should be(a[ProjectStage.IntegrationTest])
  }
}
