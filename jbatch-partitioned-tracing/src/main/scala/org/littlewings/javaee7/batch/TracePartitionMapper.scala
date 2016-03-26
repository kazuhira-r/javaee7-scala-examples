package org.littlewings.javaee7.batch

import javax.batch.api.partition.{PartitionMapper, PartitionPlan, PartitionPlanImpl}
import javax.enterprise.context.Dependent
import javax.inject.Named

import org.jboss.logging.Logger

@Dependent
@Named
class TracePartitionMapper extends PartitionMapper {
  var logger: Logger = Logger.getLogger(getClass)

  override def mapPartitions(): PartitionPlan = {
    logger.infof(s"[${Thread.currentThread.getName}] ${getClass.getName}#mapPartitions.")

    val range = 3
    val (partitionSize, threadSize) = (4, 4)

    val partitionPlan = new PartitionPlanImpl
    partitionPlan.setPartitions(partitionSize)
    partitionPlan.setThreads(threadSize)
    partitionPlan.setPartitionProperties((1 to partitionSize).map { i =>
      val properties = new java.util.Properties
      properties.setProperty("partition.start", ((i - 1) * 3).toString)
      properties
    }.toArray)

    partitionPlan
  }
}
