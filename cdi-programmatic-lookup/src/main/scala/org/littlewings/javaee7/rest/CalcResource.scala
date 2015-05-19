package org.littlewings.javaee7.rest

import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Instance
import javax.enterprise.inject.spi.{Bean, BeanManager, CDI}
import javax.inject.Inject
import javax.naming.InitialContext
import javax.ws.rs._
import javax.ws.rs.core.MediaType

import org.littlewings.javaee7.service.CalcService

@Path("calc")
@RequestScoped
class CalcResource {
  @Inject
  private var beanManager: BeanManager = _

  @GET
  @Path("beanManagerInject")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def beanManagerInject(@QueryParam("a") @DefaultValue("0") a: Int, @QueryParam("b") @DefaultValue("0") b: Int): Int = {
    val beans = beanManager.getBeans(classOf[CalcService])
    val bean = beanManager.resolve[CalcService](beans.asInstanceOf[java.util.Set[Bean[_ <: CalcService]]])

    beanManager
      .getReference(bean, classOf[CalcService], beanManager.createCreationalContext(bean))
      .asInstanceOf[CalcService]
      .add(a, b)
  }

  @GET
  @Path("beanManagerLookup")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def beanManagerLookup(@QueryParam("a") @DefaultValue("0") a: Int, @QueryParam("b") @DefaultValue("0") b: Int): Int = {
    val ic = new InitialContext()
    val bm = ic.lookup("java:comp/env/BeanManager").asInstanceOf[BeanManager]
    ic.close()

    val beans = bm.getBeans(classOf[CalcService])
    val bean = bm.resolve[CalcService](beans.asInstanceOf[java.util.Set[Bean[_ <: CalcService]]])

    bm
      .getReference(bean, classOf[CalcService], bm.createCreationalContext(bean))
      .asInstanceOf[CalcService]
      .add(a, b)
  }

  @GET
  @Path("cdiUtil")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def cdiUtil(@QueryParam("a") @DefaultValue("0") a: Int, @QueryParam("b") @DefaultValue("0") b: Int): Int =
    CDI.current.select(classOf[CalcService]).get.add(a, b)

  @Inject
  private var calcServiceInstance: Instance[CalcService] = _

  @GET
  @Path("instanceLookup")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def instanceLookup(@QueryParam("a") @DefaultValue("0") a: Int, @QueryParam("b") @DefaultValue("0") b: Int): Int =
    calcServiceInstance.select().get().add(a, b)

}
