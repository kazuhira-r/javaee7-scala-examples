package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.core.MediaType

import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.littlewings.javaee7.cdi.MessageService

@Path("message")
@RequestScoped
class MessageResource {
  @Inject
  var messageService: MessageService = _

  @Inject
  var projectStage: ProjectStage = _

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def message: java.util.Map[String, String] = {
    val map = new java.util.LinkedHashMap[String, String]
    map.put("message", messageService.get)
    map.put("projectStage", projectStage.getClass.getSimpleName)
    map
  }
}
