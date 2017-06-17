package org.littlewings.javaee7.batch

import java.util.Properties
import java.util.concurrent.TimeUnit
import javax.batch.runtime.{BatchRuntime, BatchStatus}

import org.jberet.runtime.JobExecutionImpl
import org.jboss.logging.Logger
import org.scalatest.{FunSuite, Matchers}

class BatchJdbcJobRepositorySpec extends FunSuite with Matchers {
  val logger: Logger = Logger.getLogger(getClass)

  test("success case") {
    logger.info("[success case] start")

    val jobOperator = BatchRuntime.getJobOperator

    val properties = new Properties
    properties.put("readerFail", "false")
    properties.put("readerFailCount", "0")
    properties.put("batchletFail", "false")

    val executionId = jobOperator.start("my-job", properties)

    val jobExecution = jobOperator.getJobExecution(executionId)
    jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

    jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)

    logger.infof("[success case] end, execution-id = %d", executionId)
  }

  test("reader fail and restart") {
    logger.info("[reader fail and restart] start")

    val jobOperator = BatchRuntime.getJobOperator

    // first fail
    val p1 = new Properties
    p1.put("readerFail", "true")
    p1.put("readerFailCount", "45")
    p1.put("batchletFail", "false")

    val executionId = jobOperator.start("my-job", p1)

    val jobExecution = jobOperator.getJobExecution(executionId)
    jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0L, TimeUnit.SECONDS)

    jobExecution.getBatchStatus should be(BatchStatus.FAILED)

    logger.info("first job end.")

    // retry
    val p2 = new Properties
    p2.put("readerFail", "false")
    p2.put("readerFailCount", "0")
    p2.put("batchletFail", "false")

    val restartExecutionId = jobOperator.restart(executionId, p2)

    executionId should not be (restartExecutionId)

    val restartedJobExecution = jobOperator.getJobExecution(restartExecutionId)
    restartedJobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0L, TimeUnit.SECONDS)

    restartedJobExecution.getBatchStatus should be(BatchStatus.COMPLETED)

    logger.info("restart job end")

    logger.infof("[reader fail and restart] end, execution-id first = %d, restarted = %d", executionId, restartExecutionId)
  }

  test("batchlet fail and restart") {
    logger.info("[batchlet fail and restart] start")

    val jobOperator = BatchRuntime.getJobOperator

    // first fail
    val p1 = new Properties
    p1.put("readerFail", "false")
    p1.put("readerFailCount", "0")
    p1.put("batchletFail", "true")

    val executionId = jobOperator.start("my-job", p1)

    val jobExecution = jobOperator.getJobExecution(executionId)
    jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0L, TimeUnit.SECONDS)

    jobExecution.getBatchStatus should be(BatchStatus.FAILED)

    logger.info("first job end.")

    // retry
    val p2 = new Properties
    p2.put("readerFail", "false")
    p2.put("readerFailCount", "0")
    p2.put("batchletFail", "false")

    val restartedExecutionId = jobOperator.restart(executionId, p2)

    executionId should not be (restartedExecutionId)

    val restartedJobExecution = jobOperator.getJobExecution(restartedExecutionId)
    restartedJobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0L, TimeUnit.SECONDS)

    restartedJobExecution.getBatchStatus should be(BatchStatus.COMPLETED)

    logger.info("restart job end")

    logger.infof("[batchlet fail and restart] end, execution-id first = %d, restarted = %d", executionId, restartedExecutionId)
  }
}
