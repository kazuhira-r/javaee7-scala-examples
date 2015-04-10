package org.littlewings.javaee7.rest.sub

import javax.ws.rs.{ POST, Path, Produces }
import javax.ws.rs.core.MediaType

@Path("sub")
class SubResource {
  @POST
  @Path("hello")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def hello: String = "Hello World"
}
