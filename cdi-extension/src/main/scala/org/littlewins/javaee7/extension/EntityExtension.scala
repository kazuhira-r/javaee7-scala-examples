package org.littlewings.javaee7.extension

import scala.collection.mutable

import javax.enterprise.event.Observes
import javax.enterprise.inject.spi._
import javax.persistence.Entity

class EntityExtension extends Extension {
  val entityClasses: mutable.Set[Class[_]] = mutable.Set.empty

  def collectEntity(@Observes @WithAnnotations(Array(classOf[Entity])) pat: ProcessAnnotatedType[_]): Unit = {
    val annotatedType = pat.getAnnotatedType
    entityClasses += annotatedType.getJavaClass
  }
}
