package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class SimpleProjectStageSpec extends JUnitSuite with Matchers {
  @Inject
  var messageService: MessageService = _

  @Test
  def messageTest(): Unit = {
    messageService.get should be("UnitTestStage!!")
  }

  @Inject
  var projectStage: ProjectStage = _

  @Test
  def projectStageTest(): Unit = {
    projectStage should be(a[ProjectStage.UnitTest])
  }

  @Inject
  var greetingService: GreetingService = _

  @Test
  def greetingTest(): Unit = {
    greetingService.greet should be("Hello CDI!!")
  }
}
