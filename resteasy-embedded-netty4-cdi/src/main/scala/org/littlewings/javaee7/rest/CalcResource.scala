package org.littlewings.javaee7.rest

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

@Path("calc")
@ApplicationScoped
class CalcResource {
  @Inject
  private var calcService: CalcService = _

  @GET
  @Path("add")
  @Produces(value = Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    calcService.add(a, b)
}
