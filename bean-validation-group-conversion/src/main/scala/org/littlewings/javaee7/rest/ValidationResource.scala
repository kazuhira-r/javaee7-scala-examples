package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.validation.Valid
import javax.validation.groups.{ConvertGroup, Default}
import javax.ws.rs.core.MediaType
import javax.ws.rs.{POST, Path, Produces}

import org.littlewings.javaee7.beanvalidation.{GroupA, User, User2}

@Path("validation")
@RequestScoped
class ValidationResource {
  @Path("default")
  @POST
  @Produces(Array(MediaType.APPLICATION_JSON))
  def defaultGroup(@Valid user: User): User =
    user

  @Path("convertGroupA")
  @POST
  @Produces(Array(MediaType.APPLICATION_JSON))
  def convertGroupA(@Valid @ConvertGroup(from = classOf[Default], to = classOf[GroupA]) user: User): User =
    user

  @Path("default2")
  @POST
  @Produces(Array(MediaType.APPLICATION_JSON))
  def default2(@Valid user: User2): User2 =
    user

  @Path("convertGroupA2")
  @POST
  @Produces(Array(MediaType.APPLICATION_JSON))
  def convertGroupA2(@Valid @ConvertGroup(from = classOf[Default], to = classOf[GroupA]) user: User2): User2 =
    user
}
