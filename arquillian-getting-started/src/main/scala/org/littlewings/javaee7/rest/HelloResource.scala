package org.littlewings.javaee7.rest

import javax.inject.Inject
import javax.ws.rs.{GET, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType

import org.apache.commons.lang3.StringUtils

import org.littlewings.javaee7.service.CalcService

@Path("hello")
class HelloResource {
  @Inject
  private var calcService: CalcService = _

  @GET
  @Path("index")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def index: String =
    StringUtils.repeat("Hello World ", 2).trim

  @GET
  @Path("add")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("p1") p1: Int, @QueryParam("p2") p2: Int): String =
    calcService.add(p1, p2).toString
}
