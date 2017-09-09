package org.littlewings.javaee7.resteasy

import javax.ws.rs.{Consumes, Produces}
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.{ContextResolver, Provider}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@Provider
@Consumes(Array(MediaType.APPLICATION_JSON))
@Produces(Array(MediaType.APPLICATION_JSON))
class ScalaObjectMapperProvider extends ContextResolver[ObjectMapper] {
  override def getContext(typ: Class[_]): ObjectMapper = {
    val objectMapper = new ObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }
}
