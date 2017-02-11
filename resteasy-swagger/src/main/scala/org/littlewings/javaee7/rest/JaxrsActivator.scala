package org.littlewings.javaee7.rest

import javax.ws.rs.{ApplicationPath, Path}
import javax.ws.rs.core.Application

import io.swagger.jaxrs.config.BeanConfig
import io.swagger.jaxrs.listing.{ApiListingResource, SwaggerSerializers}
import org.reflections.Reflections

import scala.collection.JavaConverters._

@ApplicationPath("api")
class JaxrsActivator extends Application {
  val beanConfig = new BeanConfig
  beanConfig.setVersion("1.0.0");
  beanConfig.setSchemes(Array("http"))
  beanConfig.setHost("localhost:8080")
  beanConfig.setBasePath(getClass.getAnnotation(classOf[ApplicationPath]).value)
  beanConfig.setResourcePackage(classOf[JaxrsActivator].getPackage.getName)
  beanConfig.setScan(true)

  override def getClasses: java.util.Set[Class[_]] = {
    val resourceClasses: Set[Class[_]] =
      Set.empty ++
        new Reflections(classOf[JaxrsActivator].getPackage.getName)
          .getTypesAnnotatedWith(classOf[Path])
          .asScala
    val swaggerClasses = Set[Class[_]](
      classOf[ApiListingResource],
      classOf[SwaggerSerializers]
    )

    (resourceClasses ++ swaggerClasses).asJava
  }
}
