package org.littlewings.javaee7.batch

import java.io.BufferedOutputStream
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.zip.GZIPOutputStream
import javax.batch.api.AbstractBatchlet
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import scala.collection.mutable.ArrayBuffer

@Dependent
@Named
class UserDataCsvArchiveBatchlet extends AbstractBatchlet {
  @Inject
  private var jobContext: JobContext = _

  override def process(): String = {
    val contentsBinary =
      jobContext
        .getTransientUserData
        .asInstanceOf[ArrayBuffer[String]]
        .mkString(System.lineSeparator)
        .getBytes(StandardCharsets.UTF_8)

    val outputArchiveCsvFilePath = Paths.get(jobContext.getProperties.getProperty("outputArchiveCsvFilePath"))
    val os = new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(outputArchiveCsvFilePath)))

    try {
      os.write(contentsBinary)
      os.finish()
    } finally {
      os.close()
    }

    jobContext.setExitStatus("BATCHLET-SUCCESS")

    "COMPLETE"
  }
}
