package org.littlewings.javaee7.resteasy

import java.net.InetSocketAddress
import javax.ws.rs.core.MediaType
import javax.ws.rs.{POST, Path, Produces}

import com.sun.net.httpserver.HttpServer
import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder
import org.scalatest.{BeforeAndAfterAll, Suite}

trait RestEasyJdkHttpServerSpecSupport extends Suite with BeforeAndAfterAll {
  val server: HttpServer = HttpServer.create(new InetSocketAddress(8080), 10)

  override def beforeAll(): Unit = {
    val contextBuilder = new HttpContextBuilder
    contextBuilder.getDeployment.getActualResourceClasses.add(classOf[PersonResource])

    val context = contextBuilder.bind(server)

    server.start()
  }

  override def afterAll(): Unit = {
    server.stop(0)
  }
}

@Path("person")
class PersonResource {
  @POST
  @Produces(Array(MediaType.APPLICATION_JSON))
  def accept(person: Person): Person =
    Person("ワカメ", person.lastName, 9)
}
