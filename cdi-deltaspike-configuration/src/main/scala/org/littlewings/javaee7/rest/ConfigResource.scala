package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import org.littlewings.javaee7.config.{DeltaSpikeConfig, MyApplicationConfig}

@Path("config")
@RequestScoped
class ConfigResource {
  @Inject
  var deltaSpikeConfig: DeltaSpikeConfig = _

  @Inject
  var myApplicationConfig: MyApplicationConfig = _

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def config(): java.util.Map[String, java.util.Map[String, String]] =
    Map(
      "apache-deltaspike" ->
        Map("database.server" -> deltaSpikeConfig.databaseName,
          "database.port" -> deltaSpikeConfig.databasePort.toString,
          "database.name" -> deltaSpikeConfig.databaseName,
          "jdbc.url" -> deltaSpikeConfig.jdbcUrl).asJava,
      "my-application" ->
        Map("application.name" -> myApplicationConfig.applicationName).asJava
    ).asJava
}
