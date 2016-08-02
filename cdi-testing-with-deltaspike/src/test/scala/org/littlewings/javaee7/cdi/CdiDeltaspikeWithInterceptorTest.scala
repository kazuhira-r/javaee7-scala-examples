package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class CdiDeltaspikeWithInterceptorTest extends JUnitSuite {
  @Inject
  private var applicationScopedService: TracingApplicationScopedService = _

  @Inject
  private var sessionScopedService: TracingSessionScopedService = _

  @Inject
  private var requestScopedService: TracingRequestScopedService = _

  @Inject
  private var dependentService: TracingDependentService = _

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
