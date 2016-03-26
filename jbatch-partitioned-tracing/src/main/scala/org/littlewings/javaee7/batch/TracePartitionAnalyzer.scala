package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.partition.PartitionAnalyzer
import javax.batch.runtime.BatchStatus
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TracePartitionAnalyzer extends PartitionAnalyzer {
  val logger: Logger = Logger.getLogger(getClass)

  override def analyzeCollectorData(data: Serializable): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#analyzeCollectorData, data = ${data}")

  override def analyzeStatus(batchStatus: BatchStatus, exitStatus: String): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#analyzeStatus, batchStatus = ${batchStatus}, exitStatus = ${exitStatus}")
}
