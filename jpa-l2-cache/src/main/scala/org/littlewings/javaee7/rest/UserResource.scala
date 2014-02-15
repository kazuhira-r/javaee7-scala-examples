package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.inject.Inject
import javax.ws.rs.{DELETE, GET, Path, PathParam, POST, PUT, Produces}
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.entity.User
import org.littlewings.javaee7.service.UserService

@Path("user")
class UserResource {
  @Inject
  private var userService: UserService = _

  @PUT
  @Path("create")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def createAll(users: java.util.List[User]): java.lang.Iterable[User] = {
    users.asScala.foreach(userService.create)
    users
  }

  @POST
  @Path("inc-age/{id}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def update(@PathParam("id") id: Int): User = {
    val user = userService.findById(id)
    user.age += 1
    userService.update(user)
  }

  @GET
  @Path("{id}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def find(@PathParam("id") id: Int): User =
    userService.findById(id)

  @GET
  @Path("age/{age}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def findByAge(@PathParam("age") age: Int): java.lang.Iterable[User] =
    userService.findByOverAge(age).asJava

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def findAll: java.lang.Iterable[User] =
    userService.findAllOrderById.asJava

  @DELETE
  @Path("remove/{id}")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def remove(@PathParam("id") id: Int): String = {
    userService.remove(userService.findById(id))
    "OK" + System.lineSeparator
  }

  @DELETE
  @Path("remove-all")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def removeAll(): String = {
    userService.findAllOrderById.foreach(userService.remove)
    "OK" + System.lineSeparator
  }
}
