package org.littlewings.javaee7.rest

import scala.beans.BeanProperty

import javax.ws.rs.{Consumes, Path, POST, Produces}
import javax.ws.rs.core.MediaType

@Path("hello")
class HelloResource {
  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def index(book: Book): Message =
    Message(value = s"あなたのリクエストした本の名前は、 [${book.name}] です")

  /*
  def index(book: Book): Message = {
    val message = new Message
    message.value = s"あなたのリクエストした本の名前は、 [${book.name}] です"
    message
  }
   */
}

case class Book(name: String, price: Int)
case class Message(value: String)

/*
class Book {
  @BeanProperty
  var name: String = _
  @BeanProperty
  var price: Int = _
}

class Message {
  @BeanProperty
  var value: String = _
}
*/
