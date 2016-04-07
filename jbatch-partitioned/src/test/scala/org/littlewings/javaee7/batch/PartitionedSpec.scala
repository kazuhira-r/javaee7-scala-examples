package org.littlewings.javaee7.batch

import java.util.concurrent.TimeUnit
import javax.batch.runtime.{BatchRuntime, BatchStatus}

import org.jberet.runtime.JobExecutionImpl
import org.scalatest.{FunSpec, Matchers}

class PartitionedSpec extends FunSpec with Matchers {
  describe("jBatch Partitioned") {
    it("execute") {
      val jobOperator = BatchRuntime.getJobOperator

      val jobId = "job"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
    }
  }
}
