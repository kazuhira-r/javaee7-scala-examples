package org.littlewings.javaee7.extension

import scala.collection.mutable

import javax.enterprise.event.Observes
import javax.enterprise.inject.spi._
import javax.ws.rs.POST

class PostExtension extends Extension {
  val postClasses: mutable.Set[Class[_]] = mutable.Set.empty

  def collectEntity(@Observes @WithAnnotations(Array(classOf[POST])) pat: ProcessAnnotatedType[_]): Unit = {
    val annotatedType = pat.getAnnotatedType
    postClasses += annotatedType.getJavaClass
  }
}
