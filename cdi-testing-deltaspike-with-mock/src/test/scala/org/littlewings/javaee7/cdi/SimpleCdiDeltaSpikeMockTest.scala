package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class SimpleCdiDeltaSpikeMockTest extends JUnitSuite with Matchers {
  @Inject
  var messageService: MessageService = _

  @Test
  def simpleCase(): Unit = {
    messageService.message should be("Hello MessageService!!")
    messageService.message("[", "]") should be("[Hello MessageService!!]")
  }

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    mockManager.addMock(new MessageService {
      override def message: String = "This is Mock!!"
    })

    messageService.message should be("This is Mock!!")
    messageService.message("[", "]") should be("[This is Mock!!]")
  }
}
