package org.littlewings.javaee7.batch

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import javax.batch.api.chunk.AbstractItemWriter
import javax.batch.runtime.context.{JobContext, StepContext}
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import scala.collection.JavaConverters._

@Dependent
@Named
class PropertiesCsvItemWriter extends AbstractItemWriter {
  @Inject
  private var jobContext: JobContext = _

  @Inject
  private var stepContext: StepContext = _

  private var writer: BufferedWriter = _

  override def open(checkpoint: java.io.Serializable): Unit = {
    writer =
      Files.newBufferedWriter(Paths.get(jobContext.getProperties.getProperty("outputCsvFilePath")),
        StandardCharsets.UTF_8
      )
  }

  override def writeItems(items: java.util.List[AnyRef]): Unit = {
    items
      .asScala
      .foreach { case item: String =>
        writer.write(item)
        writer.newLine()
      }

    stepContext.setExitStatus(stepContext.getProperties.getProperty("writeStepStatus"))
  }

  override def close(): Unit = writer.close()
}
