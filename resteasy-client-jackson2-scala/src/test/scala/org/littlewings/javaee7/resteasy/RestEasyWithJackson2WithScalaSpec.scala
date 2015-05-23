package org.littlewings.javaee7.resteasy

import javax.ws.rs.client.{Entity, ClientBuilder}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class RestEasyWithJackson2WithScalaSpec extends FunSpec with RestEasyJdkHttpServerSpecSupport {
  describe("RESTEasy with Jackson2 with ScalaModule") {
    it("case class") {
      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/person")
            .request
            .post(Entity.json(Person("カツオ", "磯野", 11)))

        response.getStatus should be(200)
        response.readEntity(classOf[Person]) should be(Person("ワカメ", "磯野", 9))

        response.close()
      } finally {
        client.close()
      }
    }
  }
}
