package org.littlewings.javaee7.batch

import javax.batch.api.chunk.AbstractItemWriter
import javax.batch.runtime.context.StepContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

@Dependent
@Named
class MyItemWriter extends AbstractItemWriter {
  @Inject
  var stepContext: StepContext = _

  override def writeItems(items: java.util.List[AnyRef]): Unit =
    stepContext.getTransientUserData match {
      case null =>
        val buffer = new ArrayBuffer[String]
        items.asScala.foreach(item => buffer += item.asInstanceOf[String])
        stepContext.setTransientUserData(buffer)
      case buffer =>
        items.asScala.foreach(item => buffer.asInstanceOf[ArrayBuffer[String]] += item.asInstanceOf[String])
        stepContext.setTransientUserData(buffer)
    }
}
