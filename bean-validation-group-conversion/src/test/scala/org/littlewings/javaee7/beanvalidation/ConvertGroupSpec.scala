package org.littlewings.javaee7.beanvalidation

import javax.ws.rs.client.{ClientBuilder, Entity}
import javax.ws.rs.core.Response

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ConvertGroupSpec extends FunSpec with EmbeddedTomcatCdiSupport {
  describe("ConvertGroup Spec") {
    it("Default Group, valid") {
      val user = new User
      user.name = new Name
      user.name.first = "カツオ"
      user.name.last = "磯野"
      user.age = 12

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        val responseEntity = response.readEntity(classOf[User])
        responseEntity.name.first = "カツオ"
        responseEntity.name.last = "磯野"

        response.close()
      } finally {
        client.close()
      }
    }

    it("Default Group, invalid-1") {
      val user = new User
      user.name = new Name
      user.name.first = "カツオ"
      user.name.last = "磯野？"
      user.age = 12

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("Default Group, invalid-2") {
      val user = new User
      user.name = new Name
      user.name.first = "カツオ"
      user.name.last = "磯野"
      user.age = 20

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA, valid") {
      val user = new User
      user.name = new Name
      user.name.first = "サザエ"
      user.name.last = "フグ田"
      user.age = 24

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        val responseEntity = response.readEntity(classOf[User])
        responseEntity.name.first = "サザエ"
        responseEntity.name.last = "フグ田"

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA, invalid-1") {
      val user = new User
      user.name = new Name
      user.name.first = "サザエ"
      user.name.last = "フグ田？"
      user.age = 24

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA, invalid-2") {
      val user = new User
      user.name = new Name
      user.name.first = "サザエ"
      user.name.last = "フグ田"
      user.age = 35

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("Default Group 2, valid") {
      val user = new User2
      user.name = new Name2
      user.name.first = "カツオ"
      user.name.last = "磯野"
      user.age = 12

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        val responseEntity = response.readEntity(classOf[User2])
        responseEntity.name.first = "カツオ"
        responseEntity.name.last = "磯野"

        response.close()
      } finally {
        client.close()
      }
    }

    it("Default Group 2, invalid-1") {
      val user = new User2
      user.name = new Name2
      user.name.first = "カツオ"
      user.name.last = "磯野？"
      user.age = 12

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("Default Group 2, invalid-2") {
      val user = new User2
      user.name = new Name2
      user.name.first = "カツオ"
      user.name.last = "磯野"
      user.age = 20

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/default2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA 2, valid") {
      val user = new User2
      user.name = new Name2
      user.name.first = "サザエ"
      user.name.last = "フグ田"
      user.age = 24

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.OK.getStatusCode)
        val responseEntity = response.readEntity(classOf[User2])
        responseEntity.name.first = "サザエ"
        responseEntity.name.last = "フグ田"

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA 2, invalid-1") {
      val user = new User2
      user.name = new Name2
      user.name.first = "サザエ"
      user.name.last = "フグ田？"
      user.age = 24

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }

    it("GroupA 2, invalid-2") {
      val user = new User2
      user.name = new Name2
      user.name.first = "サザエ"
      user.name.last = "フグ田"
      user.age = 35

      val client = ClientBuilder.newBuilder.build

      try {
        val response =
          client
            .target("http://localhost:8080/rest/validation/convertGroupA2")
            .request
            .post(Entity.json(user))

        response.getStatus should be(Response.Status.BAD_REQUEST.getStatusCode)

        response.close()
      } finally {
        client.close()
      }
    }
  }
}
