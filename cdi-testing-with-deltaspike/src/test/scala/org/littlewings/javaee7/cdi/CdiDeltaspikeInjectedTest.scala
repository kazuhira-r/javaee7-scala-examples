package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class CdiDeltaspikeInjectedTest extends JUnitSuite {
  @Inject
  private var applicationScopedService: InjectedApplicationScopedService = _

  @Inject
  private var sessionScopedService: InjectedSessionScopedService = _

  @Inject
  private var requestScopedService: InjectedRequestScopedService = _

  @Inject
  private var dependentService: InjectedDependentService = _

  @Test
  def runTest1(): Unit = {
    println("===== runTest1 =====")
    applicationScopedService.say()
    println()
    sessionScopedService.say()
    println()
    requestScopedService.say()
    println()
    dependentService.say()
    println()
  }

  @Test
  def runTest2(): Unit = {
    println("===== runTest2 =====")
    applicationScopedService.say()
    println()
    sessionScopedService.say()
    println()
    requestScopedService.say()
    println()
    dependentService.say()
    println()
  }
}
