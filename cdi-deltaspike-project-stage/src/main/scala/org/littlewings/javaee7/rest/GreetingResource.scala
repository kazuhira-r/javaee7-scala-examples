package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.littlewings.javaee7.cdi.GreetingService

@Path("greeting")
@RequestScoped
class GreetingResource {
  @Inject
  var greetingService: GreetingService = _

  @Inject
  var projectStage: ProjectStage = _

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def greeting: java.util.Map[String, String] = {
    val map = new java.util.LinkedHashMap[String, String]
    map.put("greeting", greetingService.greet)
    map.put("projectStage", projectStage.getClass.getSimpleName)
    map
  }
}
