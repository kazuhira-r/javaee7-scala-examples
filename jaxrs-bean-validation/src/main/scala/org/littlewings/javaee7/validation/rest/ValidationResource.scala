package org.littlewings.javaee7.validation.rest

import javax.validation.Valid
import javax.validation.constraints.{Digits, NotNull}
import javax.ws.rs.{Consumes, DefaultValue, GET, Path, Produces, POST, QueryParam}
import javax.ws.rs.core.MediaType

@Path("validation")
class ValidationResource {
  @GET
  @Path("simple")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def simple(@QueryParam("name") @NotNull name: String,
             @QueryParam("num") @DefaultValue("0") @Digits(integer = 3, fraction = 0) num: String): String =
    s"name = $name, num = $num${System.lineSeparator}"

  @POST
  @Path("complex")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def complex(@Valid param: Param): Res =
    Res(param.name, Some(param.num).getOrElse("0").toInt, param.subs)
}
