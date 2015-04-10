package org.littlewings.javaee7.extension

import scala.collection.mutable

import javax.enterprise.event.Observes
import javax.enterprise.inject.spi._

class MyExtension extends Extension {
  val classes: mutable.Set[Class[_]] = mutable.Set.empty

  def collectClasses(@Observes pat: ProcessAnnotatedType[_]): Unit = {
    val annotatedType = pat.getAnnotatedType
    classes += annotatedType.getJavaClass
  }
}
