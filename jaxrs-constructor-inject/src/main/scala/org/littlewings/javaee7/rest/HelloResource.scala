package org.littlewings.javaee7.rest

import javax.ws.rs.{DefaultValue, GET, Path, Produces, QueryParam}
import javax.ws.rs.core.{Context, MediaType, UriInfo}

@Path("hello")
class HelloResource(@Context uriInfo: UriInfo) {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def sayHello(@QueryParam("name") @DefaultValue("world") name: String): String =
    s"Hello, $name!"

  @GET
  @Path("path")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def path: String =
    uriInfo.getPath
}

@Path("hello-val")
class HelloValResource(@Context val uriInfo: UriInfo) {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def sayHello(@QueryParam("name") @DefaultValue("world") name: String): String =
    s"Hello, $name!"

  @GET
  @Path("path")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def path: String =
    uriInfo.getPath
}
