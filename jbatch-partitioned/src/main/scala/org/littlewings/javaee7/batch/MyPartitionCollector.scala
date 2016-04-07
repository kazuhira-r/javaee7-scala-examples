package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.BatchProperty
import javax.batch.api.partition.PartitionCollector
import javax.batch.runtime.context.StepContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import scala.collection.mutable.ArrayBuffer

@Dependent
@Named
class MyPartitionCollector extends PartitionCollector {
  @Inject
  @BatchProperty
  var prefix: String = _

  @Inject
  @BatchProperty
  var suffix: String = _

  @Inject
  var stepContext: StepContext = _

  override def collectPartitionData(): Serializable = {
    val buffer = stepContext.getTransientUserData.asInstanceOf[ArrayBuffer[String]]

    stepContext.setTransientUserData(null)

    if (buffer != null)
      buffer.map(prefix + _ + suffix).mkString(s"processed thread[${Thread.currentThread.getName}] ", ", ", "")
    else
      s"processed thread[${Thread.currentThread.getName}] empty"
  }
}
