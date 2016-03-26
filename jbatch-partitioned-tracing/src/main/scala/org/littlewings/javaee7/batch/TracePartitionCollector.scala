package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.partition.PartitionCollector
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TracePartitionCollector extends PartitionCollector {
  val logger: Logger = Logger.getLogger(getClass)

  override def collectPartitionData(): Serializable = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#collectPartitionData")
    s"COLLECTED-${Thread.currentThread.getName}"
  }
}
