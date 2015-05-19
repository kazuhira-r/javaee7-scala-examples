package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Instance
import javax.enterprise.inject.spi.{Bean, BeanManager, CDI}
import javax.enterprise.util.AnnotationLiteral
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import org.littlewings.javaee7.qualifier.{HelloGroovy, HelloScala}
import org.littlewings.javaee7.service.MessageService

@Path("message")
@RequestScoped
class MessageResource {
  @Inject
  private var beanManager: BeanManager = _

  @GET
  @Path("beanManagerDefault")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def beanManagerDefault: String = {
    val beans = beanManager.getBeans(classOf[MessageService])
    val bean = beanManager.resolve[MessageService](beans.asInstanceOf[java.util.Set[Bean[_ <: MessageService]]])

    beanManager
      .getReference(bean, classOf[MessageService], beanManager.createCreationalContext(bean))
      .asInstanceOf[MessageService]
      .get
  }

  @GET
  @Path("beanManagerWithQualifier")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def beanManagerWithQualifier: String = {
    val beans = beanManager.getBeans(classOf[MessageService], new AnnotationLiteral[HelloScala] {})
    val bean = beanManager.resolve[MessageService](beans.asInstanceOf[java.util.Set[Bean[_ <: MessageService]]])

    beanManager
      .getReference(bean, classOf[MessageService], beanManager.createCreationalContext(bean))
      .asInstanceOf[MessageService]
      .get
  }

  @GET
  @Path("cdiUtil")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def cdiUtil: String =
    CDI
      .current
      .select(classOf[MessageService], new AnnotationLiteral[HelloGroovy] {})
      .get
      .get

  @Inject
  @javax.enterprise.inject.Any
  private var messageServiceInstance: Instance[MessageService] = _

  @GET
  @Path("instanceLookup")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def instanceLookup: String =
    messageServiceInstance
      .select(new AnnotationLiteral[HelloScala] {})
      .get
      .get
}
