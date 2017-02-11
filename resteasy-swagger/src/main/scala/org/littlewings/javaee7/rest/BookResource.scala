package org.littlewings.javaee7.rest

import javax.ws.rs.core.{Context, MediaType, Response, UriInfo}
import javax.ws.rs._

import io.swagger.annotations.{Api, ApiOperation}

import scala.collection.JavaConverters._

object BookResource {
  private[rest] val books: scala.collection.mutable.Map[String, Book] =
    new java.util.concurrent.ConcurrentHashMap[String, Book]().asScala
}

@Path("book")
@Api("book")
class BookResource {
  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  @ApiOperation(value = "find all books", response = classOf[Seq[Book]])
  def fildAll: Seq[Book] =
    BookResource.books.values.toVector

  @GET
  @Path("{isbn}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  @ApiOperation(value = "find book", response = classOf[Book])
  def find(@PathParam("isbn") isbn: String): Book =
    BookResource.books.get(isbn).orNull

  @PUT
  @Path("{isbn}")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @ApiOperation("register book")
  def register(book: Book, @Context uriInfo: UriInfo): Response = {
    BookResource.books.put(book.isbn, book)
    Response.created(uriInfo.getRequestUriBuilder.build(book.isbn)).build
  }
}
