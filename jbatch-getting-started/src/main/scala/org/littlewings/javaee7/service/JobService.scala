package org.littlewings.javaee7.service

import java.util.Properties
import javax.batch.runtime.BatchRuntime
import javax.ejb.{Schedule, Singleton}

import org.jboss.logging.Logger

import scala.util.Random

@Singleton
class JobService {
  @transient
  val logger: Logger = Logger.getLogger(getClass)

  @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
  def executeJob(): Unit = {
    logger.infof("start job service.")

    val properties = new Properties
    properties.setProperty("id", (new Random().nextInt(5) + 1).toString)

    val jobOperator = BatchRuntime.getJobOperator
    val executionId = jobOperator.start("myJob", properties)

    logger.infof("end job service, executionId[%d]", executionId)
  }
}
