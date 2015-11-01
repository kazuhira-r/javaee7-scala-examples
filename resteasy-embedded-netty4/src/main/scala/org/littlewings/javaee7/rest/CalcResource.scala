package org.littlewings.javaee7.rest

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

@Path("calc")
class CalcResource {
  @GET
  @Path("add")
  @Produces(value = Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    a + b
}
