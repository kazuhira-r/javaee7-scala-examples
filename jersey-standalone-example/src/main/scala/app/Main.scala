package app

import java.net.URI

import org.glassfish.jersey.filter.LoggingFilter
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig

object Main {
  def main(args: Array[String]): Unit = {
    val uri = URI.create("http://localhost:8080/")

    val config = new ResourceConfig

    config.packages(true, "app")
    config.register(classOf[LoggingFilter])

    JdkHttpServerFactory.createHttpServer(uri, config)
  }
}
