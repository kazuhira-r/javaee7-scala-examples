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
class CascadingCdiDeltaSpikeMockWithMockitoWithInterceptorTest extends JUnitSuite with Matchers {
  @Inject
  var wrappedService: TracingWrappedService = _

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    val messageServiceMock = mock(classOf[TracingMessageService])
    when(messageServiceMock.message).thenReturn("Hello Mock!!")
    when(messageServiceMock.message("[", "]")).thenReturn("[Hello Mock!!]")

    mockManager.addMock(messageServiceMock)

    wrappedService.message should be("Hello MessageService!!")
    wrappedService.message("[", "]") should be("[Hello MessageService!!]")
  }

  @Test
  def withSpy(): Unit = {
    val messageServiceSpy = spy(classOf[TracingMessageService])
    when(messageServiceSpy.message).thenReturn("Hello Mock!!")

    mockManager.addMock(messageServiceSpy)

    wrappedService.message should be("Hello MessageService!!")
    wrappedService.message("[", "]") should be("[Hello MessageService!!]")
  }

  @Test
  def withMockSelfAssign(): Unit = {
    val messageServiceMock = mock(classOf[TracingMessageService])
    when(messageServiceMock.message).thenReturn("Hello Mock!!")
    when(messageServiceMock.message("[", "]")).thenReturn("[Hello Mock!!]")

    wrappedService.messageService = messageServiceMock

    wrappedService.message should be("Hello Mock!!")
    wrappedService.message("[", "]") should be("[Hello Mock!!]")
  }

  @Test
  def withSpySelfAssign(): Unit = {
    val messageServiceSpy = spy(classOf[TracingMessageService])
    when(messageServiceSpy.message).thenReturn("Hello Mock!!")

    wrappedService.messageService = messageServiceSpy

    wrappedService.message should be("Hello Mock!!")
    wrappedService.message("[", "]") should be("[Hello Mock!!]")
  }
}
