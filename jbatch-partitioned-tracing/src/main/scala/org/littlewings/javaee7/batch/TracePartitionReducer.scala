package org.littlewings.javaee7.batch

import javax.batch.api.partition.PartitionReducer
import javax.batch.api.partition.PartitionReducer.PartitionStatus
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TracePartitionReducer extends PartitionReducer {
  var logger: Logger = Logger.getLogger(getClass)

  override def rollbackPartitionedStep(): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#rollbackPartitionedStep")

  override def beginPartitionedStep(): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#beginPartitionedStep")

  override def beforePartitionedStepCompletion(): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#beforePartitionedStepCompletion")

  override def afterPartitionedStepCompletion(status: PartitionStatus): Unit =
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#afterPartitionedStepCompletion, status = ${status}")
}
