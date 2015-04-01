package org.littlewings.javaee7.rest

import javax.inject.Inject
import javax.ws.rs.{ GET, Path, Produces, QueryParam }
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.service.CalcService

@Path("calc")
class CalcResource {
  @Inject
  private var calcService: CalcService = _

  @GET
  @Path("add")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    calcService.add(a, b)
}
