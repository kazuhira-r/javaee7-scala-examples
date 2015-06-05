package org.littlewings.javaee7.cdi

import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.enterprise.inject.spi.InjectionPoint

import org.jboss.logging.Logger

@Dependent
class LoggerProducer {
  @Produces
  def createLogger(injectionPoint: InjectionPoint): Logger = {
    val logger = Logger.getLogger(injectionPoint.getMember.getDeclaringClass)

    logger.infof("annotated = %s", injectionPoint.getAnnotated)
    logger.infof("annotated.annotations = %s", injectionPoint.getAnnotated.getAnnotations)
    logger.infof("annotated.baseType = %s", injectionPoint.getAnnotated.getBaseType)
    logger.infof("annotated.typeClosure = %s", injectionPoint.getAnnotated.getTypeClosure)
    logger.infof("bean = %s", injectionPoint.getBean)
    logger.infof("bean.beanClass = %s", injectionPoint.getBean.getBeanClass)
    logger.infof("bean.injectionPoints = %s", injectionPoint.getBean.getInjectionPoints)
    logger.infof("bean.name = %s", injectionPoint.getBean.getName)
    logger.infof("bean.qualifier = %s", injectionPoint.getBean.getQualifiers)
    logger.infof("bean.scope = %s", injectionPoint.getBean.getScope)
    logger.infof("bean.stereotypes = %s", injectionPoint.getBean.getStereotypes)
    logger.infof("bean.types = %s", injectionPoint.getBean.getTypes)
    logger.infof("member = %s", injectionPoint.getMember)
    logger.infof("member.declaringClass = %s", injectionPoint.getMember.getDeclaringClass)
    logger.infof("member.modifiers = %s", injectionPoint.getMember.getModifiers)
    logger.infof("member.name = %s", injectionPoint.getMember.getName)
    logger.infof("qualifier = %s", injectionPoint.getQualifiers)
    logger.infof("type = %s", injectionPoint.getType)
    logger.infof("delegate = %b", injectionPoint.isDelegate)
    logger.infof("transient = %b", injectionPoint.isTransient)

    logger
  }

  @LoggerQualifier
  @Produces
  def createLoggerWithQualifier(injectionPoint: InjectionPoint): Logger = {
    val logger = Logger.getLogger(injectionPoint.getMember.getDeclaringClass)

    logger.infof("annotated = %s", injectionPoint.getAnnotated)
    logger.infof("annotated.annotations = %s", injectionPoint.getAnnotated.getAnnotations)
    logger.infof("annotated.baseType = %s", injectionPoint.getAnnotated.getBaseType)
    logger.infof("annotated.typeClosure = %s", injectionPoint.getAnnotated.getTypeClosure)
    logger.infof("bean = %s", injectionPoint.getBean)
    logger.infof("bean.beanClass = %s", injectionPoint.getBean.getBeanClass)
    logger.infof("bean.injectionPoints = %s", injectionPoint.getBean.getInjectionPoints)
    logger.infof("bean.name = %s", injectionPoint.getBean.getName)
    logger.infof("bean.qualifier = %s", injectionPoint.getBean.getQualifiers)
    logger.infof("bean.scope = %s", injectionPoint.getBean.getScope)
    logger.infof("bean.stereotypes = %s", injectionPoint.getBean.getStereotypes)
    logger.infof("bean.types = %s", injectionPoint.getBean.getTypes)
    logger.infof("member = %s", injectionPoint.getMember)
    logger.infof("member.declaringClass = %s", injectionPoint.getMember.getDeclaringClass)
    logger.infof("member.modifiers = %s", injectionPoint.getMember.getModifiers)
    logger.infof("member.name = %s", injectionPoint.getMember.getName)
    logger.infof("qualifier = %s", injectionPoint.getQualifiers)
    logger.infof("type = %s", injectionPoint.getType)
    logger.infof("delegate = %b", injectionPoint.isDelegate)
    logger.infof("transient = %b", injectionPoint.isTransient)

    logger
  }

  @ManualLoggerQualifier
  @Produces
  def createLoggerWithQualifierFromManual(injectionPoint: InjectionPoint): Logger = {
    val logger =
      if (injectionPoint.getMember != null) Logger.getLogger(injectionPoint.getMember.getDeclaringClass)
      else Logger.getLogger(getClass)

    logger.infof("annotated = %s", injectionPoint.getAnnotated)

    if (injectionPoint.getAnnotated != null) {
      logger.infof("annotated.annotations = %s", injectionPoint.getAnnotated.getAnnotations)
      logger.infof("annotated.baseType = %s", injectionPoint.getAnnotated.getBaseType)
      logger.infof("annotated.typeClosure = %s", injectionPoint.getAnnotated.getTypeClosure)
    }

    logger.infof("bean = %s", injectionPoint.getBean)

    if (injectionPoint.getBean != null) {
      logger.infof("bean.beanClass = %s", injectionPoint.getBean.getBeanClass)
      logger.infof("bean.injectionPoints = %s", injectionPoint.getBean.getInjectionPoints)
      logger.infof("bean.name = %s", injectionPoint.getBean.getName)
      logger.infof("bean.qualifier = %s", injectionPoint.getBean.getQualifiers)
      logger.infof("bean.scope = %s", injectionPoint.getBean.getScope)
      logger.infof("bean.stereotypes = %s", injectionPoint.getBean.getStereotypes)
      logger.infof("bean.types = %s", injectionPoint.getBean.getTypes)
      logger.infof("member = %s", injectionPoint.getMember)
    }

    if (injectionPoint.getMember != null) {
      logger.infof("member.declaringClass = %s", injectionPoint.getMember.getDeclaringClass)
      logger.infof("member.modifiers = %s", injectionPoint.getMember.getModifiers)
      logger.infof("member.name = %s", injectionPoint.getMember.getName)
    }

    logger.infof("qualifier = %s", injectionPoint.getQualifiers)
    logger.infof("type = %s", injectionPoint.getType)
    logger.infof("delegate = %b", injectionPoint.isDelegate)
    logger.infof("transient = %b", injectionPoint.isTransient)

    logger
  }
}
