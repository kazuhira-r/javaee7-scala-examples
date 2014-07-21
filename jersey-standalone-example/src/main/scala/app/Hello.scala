package app

import javax.ws.rs.{DefaultValue, GET, Path, Produces, QueryParam}
import javax.ws.rs.core.MediaType

@Path("hello")
class Hello {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def say(@QueryParam("name") @DefaultValue("world") name: String): String =
    s"Hello, $name"
}
