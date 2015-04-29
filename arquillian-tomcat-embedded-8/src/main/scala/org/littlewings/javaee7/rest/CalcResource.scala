package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs._
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.service.CalcService

@Path("calc")
@RequestScoped
class CalcResource {
  @Inject
  private var calcService: CalcService = _

  @GET
  @Path("add")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") @DefaultValue("0") a: Int, @QueryParam("b") @DefaultValue("0") b: Int): Int =
    calcService.add(a, b)
}
