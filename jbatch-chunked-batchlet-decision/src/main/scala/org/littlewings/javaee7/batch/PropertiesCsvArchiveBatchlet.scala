package org.littlewings.javaee7.batch

import java.io.{BufferedInputStream, BufferedOutputStream}
import java.nio.file.{Files, Paths}
import java.util.zip.GZIPOutputStream
import javax.batch.api.AbstractBatchlet
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

@Dependent
@Named
class PropertiesCsvArchiveBatchlet extends AbstractBatchlet {
  @Inject
  private var jobContext: JobContext = _

  override def process(): String = {
    val outputCsvFilePath = Paths.get(jobContext.getProperties.getProperty("outputCsvFilePath"))
    val outputArchiveCsvFilePath = Paths.get(jobContext.getProperties.getProperty("outputArchiveCsvFilePath"))

    val is = new BufferedInputStream(Files.newInputStream(outputCsvFilePath))
    val os = new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(outputArchiveCsvFilePath)))

    try {
      Iterator
        .continually(is.read())
        .takeWhile(_ > -1)
        .foreach(os.write)

      os.finish()
    } finally {
      is.close()
      os.close()
    }

    jobContext.setExitStatus("BATCHLET-SUCCESS")

    "COMPLETED"
  }
}
