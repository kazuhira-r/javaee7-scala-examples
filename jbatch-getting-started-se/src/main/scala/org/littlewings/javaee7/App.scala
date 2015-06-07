package org.littlewings.javaee7

import java.util.Properties
import java.util.concurrent.TimeUnit
import javax.batch.runtime.{BatchRuntime, BatchStatus}

import org.jberet.runtime.JobExecutionImpl
import org.jboss.logging.Logger

import scala.util.Random

object App {
  def main(args: Array[String]): Unit = {
    val logger = Logger.getLogger(getClass)

    logger.infof("start job service.")

    val properties = new Properties
    properties.setProperty("id", (new Random().nextInt(5) + 1).toString)

    val jobOperator = BatchRuntime.getJobOperator
    val executionId = jobOperator.start("myJob", properties)

    val jobExecution = jobOperator.getJobExecution(executionId)
    jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

    logger.infof("end job service, executionId[%d], status[%s]", executionId, jobExecution.getExitStatus)

    val returnCode = jobExecution.getBatchStatus match {
      case BatchStatus.COMPLETED => 0
      case _ => 1
    }

    System.exit(returnCode)
  }
}
