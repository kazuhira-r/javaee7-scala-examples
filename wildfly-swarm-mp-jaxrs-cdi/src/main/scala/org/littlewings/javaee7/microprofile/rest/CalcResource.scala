package org.littlewings.javaee7.microprofile.rest

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import org.littlewings.javaee7.microprofile.service.CalcService

@Path("calc")
@RequestScoped
class CalcResource {
  @Inject
  var calcService: CalcService = _

  @Path("add")
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    calcService.add(a, b)
}
