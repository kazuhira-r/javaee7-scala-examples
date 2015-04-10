package org.littlewings.javaee7.rest

import javax.ws.rs.{ GET, Path, Produces }
import javax.ws.rs.core.MediaType

@Path("top")
class TopResource {
  @GET
  @Path("hello")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def hello: String = "Hello World"
}
