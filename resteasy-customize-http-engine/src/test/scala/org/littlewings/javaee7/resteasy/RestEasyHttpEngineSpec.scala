package org.littlewings.javaee7.resteasy

import javax.ws.rs.client.{ClientBuilder, Entity}
import javax.ws.rs.core.Response

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionEngine
import org.scalatest.{FunSuite, Matchers}

class RestEasyHttpEngineSpec extends FunSuite with Matchers {
  test("use standard engine") {
    TestRestServer.withServer {
      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/test/get")
            .request
            .get()

        response.getStatus should be(Response.Status.OK.getStatusCode)
        response.readEntity(classOf[Book]) should be(
          Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104)
        )

        response.close()
      } finally {
        client.close()
      }
    }
  }

  test("use java.net.HttpConnection engine") {
    TestRestServer.withServer {
      val client = new ResteasyClientBuilder().httpEngine(new URLConnectionEngine).build()

      try {
        val response =
          client
            .target("http://localhost:8080/test/get")
            .request
            .get()

        response.getStatus should be(Response.Status.OK.getStatusCode)
        response.readEntity(classOf[Book]) should be(
          Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104)
        )

        response.close()
      } finally {
        client.close()
      }
    }
  }

  test("use OkHttp engine") {
    TestRestServer.withServer {
      val client = new ResteasyClientBuilder().httpEngine(new OkHttpEngine).build()

      try {
        val response =
          client
            .target("http://localhost:8080/test/get")
            .request
            .get()

        response.getStatus should be(Response.Status.OK.getStatusCode)
        response.readEntity(classOf[Book]) should be(
          Book("978-4798140926", "Java EE 7徹底入門 標準Javaフレームワークによる高信頼性Webシステムの構築", 4104)
        )

        response.close()
      } finally {
        client.close()
      }
    }
  }

  test("use OkHttp engine, post") {
    TestRestServer.withServer {
      val client = new ResteasyClientBuilder().httpEngine(new OkHttpEngine).build()

      try {
        val response =
          client
            .target("http://localhost:8080/test/post")
            .request
            .post(Entity.json(Book("978-4774183169", "パーフェクト Java EE", 3456)))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        response.readEntity(classOf[Book]) should be(
          Book("978-4774183169", "パーフェクト Java EE", 6912)
        )

        response.close()
      } finally {
        client.close()
      }
    }
  }

  test("use OkHttp engine, put") {
    TestRestServer.withServer {
      val client = new ResteasyClientBuilder().httpEngine(new OkHttpEngine).build()

      try {
        val response =
          client
            .target("http://localhost:8080/test/put")
            .request
            .put(Entity.json(Book("978-4774183169", "パーフェクト Java EE", 3456)))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        response.readEntity(classOf[Book]) should be(
          Book("978-4774183169", "パーフェクト Java EE", 6912)
        )

        response.close()
      } finally {
        client.close()
      }
    }
  }

  test("use OkHttp engine, delete") {
    TestRestServer.withServer {
      val client = new ResteasyClientBuilder().httpEngine(new OkHttpEngine).build()

      try {
        val response =
          client
            .target("http://localhost:8080/test/delete")
            .request
            .method("DELETE", Entity.json(Book("978-4774183169", "パーフェクト Java EE", 3456)))

        response.getStatus should be(Response.Status.NO_CONTENT.getStatusCode)
        response.getEntity should be(null)

        response.close()
      } finally {
        client.close()
      }
    }
  }
}
