package org.littlewings.javaee7.service

import java.lang.annotation.Annotation

import javax.enterprise.inject.spi.Bean
import javax.enterprise.inject.spi.BeanManager
import javax.naming.InitialContext

object ServiceLookup {
  private[this] val ic: InitialContext = new InitialContext

  private[this] lazy val beanManager: BeanManager =
    ic.lookup("java:comp/BeanManager").asInstanceOf[BeanManager]

  def resolve[A](clazz: Class[A], qualifiers: Annotation*): A = {
    val beans = beanManager.getBeans(clazz, qualifiers: _*)
    val bean =
      beanManager
        .resolve[A](beans.asInstanceOf[java.util.Set[Bean[_ <: A]]])

    beanManager
      .getReference(bean, clazz, beanManager.createCreationalContext(bean))
      .asInstanceOf[A]
  }
}
