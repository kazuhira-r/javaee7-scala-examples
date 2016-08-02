package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class CdiDeltaspikeTest extends JUnitSuite {
  @Inject
  private var applicationScopedService: ApplicationScopedService = _

  @Inject
  private var sessionScopedService: SessionScopedService = _

  @Inject
  private var requestScopedService: RequestScopedService = _

  @Inject
  private var dependentService: DependentService = _

  @Test
  def runTest1(): Unit = {
    println("===== runTest1 =====")
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println()
  }

  @Test
  def runTest2(): Unit = {
    println("===== runTest2 =====")
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println()
  }
}
