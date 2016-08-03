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
class CascadingCdiDeltaSpikeMockWithMockitoTest extends JUnitSuite with Matchers {
  @Inject
  var wrappedService: WrappedService = _

  @Inject
  var mockManager: DynamicMockManager = _

  @Test
  def withMock(): Unit = {
    val messageServiceMock = mock(classOf[MessageService])
    when(messageServiceMock.message).thenReturn("Hello Mock!!")
    when(messageServiceMock.message("[", "]")).thenReturn("[Hello Mock!!]")

    mockManager.addMock(messageServiceMock)

    wrappedService.message should be("Hello Mock!!")
    wrappedService.message("[", "]") should be("[Hello Mock!!]")
  }

  @Test
  def withSpy(): Unit = {
    val messageServiceSpy = spy(classOf[MessageService])
    when(messageServiceSpy.message).thenReturn("Hello Mock!!")

    mockManager.addMock(messageServiceSpy)

    wrappedService.message should be("Hello Mock!!")
    wrappedService.message("[", "]") should be("[Hello Mock!!]")
  }
}
