package org.littlewings.javaee7.batch

import java.util.concurrent.TimeUnit
import javax.batch.runtime.{BatchStatus, BatchRuntime}

import org.jberet.runtime.JobExecutionImpl
import org.scalatest.{Matchers, FunSpec}

class SplitFlowSpec extends FunSpec with Matchers {
  describe("Split Flow Spec") {
    val jobOperator = BatchRuntime.getJobOperator

    it("split-success") {
      val jobId = "job"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
      jobExecution.getExitStatus should be("JOB-FINISH")
    }

    it("split-fail") {
      val jobId = "job"
      val properties = new java.util.Properties
      properties.setProperty("thrownException-3", "true")

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.FAILED)
      jobExecution.getExitStatus should be("FAILED")
    }
  }
}
