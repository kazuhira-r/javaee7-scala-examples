package org.littlewings.javaee7.cdi

import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite

@RunWith(classOf[CdiTestRunner])
class SimpleCdiDeltaSpikeMockWithMockitoWithInterceptorTest extends JUnitSuite with Matchers {
  @Inject
  var messageService: TracingMessageService = _

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    val messageServiceMock = mock(classOf[TracingMessageService])
    when(messageServiceMock.message).thenReturn("Hello Mock!!")
    when(messageServiceMock.message("[", "]")).thenReturn("Hello prefix & suffix Mock!!")

    mockManager.addMock(messageServiceMock)

    messageService.message should be("Hello MessageService!!")
    messageService.message("[", "]") should be("[Hello MessageService!!]")
  }
}
