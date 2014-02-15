package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.inject.Inject
import javax.ws.rs.{DELETE, GET, Path, PathParam, POST, PUT, Produces}
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.entity.Book
import org.littlewings.javaee7.service.BookService

@Path("book")
class BookResource {
  @Inject
  private var bookService: BookService = _

  @PUT
  @Path("create")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def createAll(books: java.util.List[Book]): java.lang.Iterable[Book] = {
    books.asScala.foreach(bookService.create)
    books
  }

  @POST
  @Path("inc-price/{isbn}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def update(@PathParam("isbn") isbn: String): Book = {
    val book = bookService.findByIsbn(isbn)
    book.price += 1000
    bookService.update(book)
  }

  @GET
  @Path("{isbn}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def find(@PathParam("isbn") isbn: String): Book =
    bookService.findByIsbn(isbn)

  @GET
  @Path("price/{price}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def findByAge(@PathParam("price") price: Int): java.lang.Iterable[Book] =
    bookService.findByOverPrice(price).asJava

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def findAll: java.lang.Iterable[Book] =
    bookService.findAll.asJava

  @DELETE
  @Path("remove/{isbn}")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def remove(@PathParam("isbn") isbn: String): String = {
    bookService.remove(bookService.findByIsbn(isbn))
    "OK" + System.lineSeparator
  }

  @DELETE
  @Path("remove-all")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def removeAll(): String = {
    bookService.findAll.foreach(bookService.remove)
    "OK" + System.lineSeparator
  }
}
