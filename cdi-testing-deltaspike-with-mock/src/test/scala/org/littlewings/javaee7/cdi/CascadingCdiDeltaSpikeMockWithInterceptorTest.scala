package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class CascadingCdiDeltaSpikeMockWithInterceptorTest extends JUnitSuite with Matchers {
  @Inject
  var wrappedService: TracingWrappedService = _

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    mockManager.addMock(new TracingMessageService() {
      override def message: String = "This is Mock!!"
    })

    wrappedService.message should be("Hello MessageService!!")
    wrappedService.message("[", "]") should be("[Hello MessageService!!]")
  }

  @Test
  def withMockSelfAssign(): Unit = {
    wrappedService.messageService = new TracingMessageService() {
      override def message: String = "This is Mock!!"
    }

    wrappedService.message should be("This is Mock!!")
    wrappedService.message("[", "]") should be("[This is Mock!!]")
  }
}
