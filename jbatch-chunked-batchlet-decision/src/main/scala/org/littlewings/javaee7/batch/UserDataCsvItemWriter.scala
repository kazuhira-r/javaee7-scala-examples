package org.littlewings.javaee7.batch

import javax.batch.api.chunk.AbstractItemWriter
import javax.batch.runtime.context.{StepContext, JobContext}
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

@Dependent
@Named
class UserDataCsvItemWriter extends AbstractItemWriter {
  @Inject
  private var jobContext: JobContext = _

  @Inject
  private var stepContext: StepContext = _

  private val buffer: mutable.ArrayBuffer[String] = new ArrayBuffer[String]

  override def writeItems(items: java.util.List[AnyRef]): Unit = {
    items
      .asScala
      .foreach { case item: String => buffer += item }

    stepContext.setExitStatus(stepContext.getProperties.getProperty("writeStepStatus"))
  }

  override def close(): Unit = jobContext.setTransientUserData(buffer)
}
