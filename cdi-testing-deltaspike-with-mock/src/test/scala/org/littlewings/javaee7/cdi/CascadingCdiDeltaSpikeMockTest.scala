package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class CascadingCdiDeltaSpikeMockTest extends JUnitSuite with Matchers {
  @Inject
  var wrappedService: WrappedService = _

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    mockManager.addMock(new MessageService() {
      override def message: String = "This is Mock!!"
    })

    wrappedService.message should be("This is Mock!!")
    wrappedService.message("[", "]") should be("[This is Mock!!]")
  }
}
