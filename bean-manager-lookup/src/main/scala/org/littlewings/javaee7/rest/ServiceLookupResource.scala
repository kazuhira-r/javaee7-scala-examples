package org.littlewings.javaee7.rest

import java.time.format.DateTimeFormatter

import javax.ws.rs.{GET, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.service.{CalcService, DateService, ServiceLookup}

@Path("service-lookup")
class ServiceLookupResource {
  @GET
  @Path("calc")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def calc(@QueryParam("p1") p1: Int, @QueryParam("p2") p2: Int): String = {
    val calcService = ServiceLookup.resolve(classOf[CalcService])
    calcService.add(p1, p2) + System.lineSeparator
  }

  @GET
  @Path("date")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def date: String = {
    val dateService = ServiceLookup.resolve(classOf[DateService])
    dateService
      .time
      .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) +
        System.lineSeparator
  }
}
