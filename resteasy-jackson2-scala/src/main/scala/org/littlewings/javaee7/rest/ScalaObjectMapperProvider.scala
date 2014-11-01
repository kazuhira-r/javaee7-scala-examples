package org.littlewings.javaee7.rest

import javax.ws.rs.ext.ContextResolver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

class ScalaObjectMapperProvider extends ContextResolver[ObjectMapper] {
  override def getContext(typ: Class[_]): ObjectMapper = {
    val objectMapper = new ObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }
}
