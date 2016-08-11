package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import org.apache.deltaspike.scheduler.spi.Scheduler
import org.littlewings.javaee7.cdi.ManualQuartzBasedJob
import org.quartz.Job

@Path("job")
@RequestScoped
class ManualJobResource {
  @Inject
  var scheculer: Scheduler[Job] = _

  @GET
  @Path("start-job")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def startJob: String = {
    scheculer.registerNewJob(classOf[ManualQuartzBasedJob])

    "Register Job!!"
  }
}
