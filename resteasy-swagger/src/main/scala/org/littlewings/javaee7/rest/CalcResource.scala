package org.littlewings.javaee7.rest

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import io.swagger.annotations.{Api, ApiOperation}

@Path("calc")
@Api("calc")
class CalcResource {
  @GET
  @Path("add")
  @Produces(Array(MediaType.TEXT_PLAIN))
  @ApiOperation(value = "calc add")
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    a + b

  @GET
  @Path("multiply")
  @Produces(Array(MediaType.TEXT_PLAIN))
  @ApiOperation(value = "calc multiply")
  def multiply(@QueryParam("a") a: Int, @QueryParam("b") b: Int): Int =
    a * b
}
