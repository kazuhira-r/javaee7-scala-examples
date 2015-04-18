package org.littlewings.javaee7.cdi.rest

import javax.enterprise.inject.spi.CDI
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.{ GET, Path, Produces }
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.cdi.service.MessageService

@Path("message")
@RequestScoped
class MessageResource {
  @Inject
  private var messageService: MessageService = _

  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def message: String = messageService.get
}
