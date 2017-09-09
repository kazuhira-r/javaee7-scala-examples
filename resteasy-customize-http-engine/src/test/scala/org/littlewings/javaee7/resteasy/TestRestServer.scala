package org.littlewings.javaee7.resteasy

import javax.ws.rs.core.MediaType
import javax.ws.rs._

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer

object TestRestServer {
  def withServer(fun: => Unit): Unit = {
    val netty = new NettyJaxrsServer
    val deployment = netty.getDeployment
    deployment.setResourceClasses(java.util.Arrays.asList(classOf[TestResource].getName))
    netty.setRootResourcePath("")
    netty.setPort(8080)
    netty.setDeployment(deployment)
    netty.start()

    try {
      fun
    } finally {
      netty.stop()
    }
  }
}

@Path("test")
class TestResource {
  @GET
  @Path("get")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def get: Book =
    Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104)

  @POST
  @Path("post")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def post(book: Book): Book = book.copy(price = book.price * 2)

  @PUT
  @Path("put")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def put(book: Book): Book = book.copy(price = book.price * 2)

  @DELETE
  @Path("delete")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def delete(book: Book): Unit = ()
}
