package org.littlewings.javaee7.rest

import scala.collection.JavaConverters._

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("rest")
class JaxrsApplication extends Application {
  override def getClasses: java.util.Set[Class[_]] =
    Set[Class[_]](classOf[CalcResource]).asJava
}
