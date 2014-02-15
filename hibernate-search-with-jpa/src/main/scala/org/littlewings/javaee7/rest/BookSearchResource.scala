package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.inject.Inject
import javax.ws.rs.{DELETE, GET, Path, PathParam, POST, PUT, Produces, QueryParam}
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.entity.Book
import org.littlewings.javaee7.service.BookSearchService

@Path("book/search")
class BookSearchResource {
  @Inject
  private var bookSearchService: BookSearchService = _

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def search(@QueryParam("title") title: String, @QueryParam("summary") summary: String): java.util.Map[String, AnyRef] = {
    val result = bookSearchService.search(title, summary)

    Map("query" -> result._1.toString,
        "hits" -> new Integer(result._2.size),
        "books" -> result._2.asJava)
          .asJava
  }

  @POST
  @Path("indexing")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def indexing: String = {
    bookSearchService.indexing()
    "OK" + System.lineSeparator
  }
}
