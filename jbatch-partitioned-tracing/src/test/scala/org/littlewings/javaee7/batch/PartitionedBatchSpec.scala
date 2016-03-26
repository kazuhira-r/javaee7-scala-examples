package org.littlewings.javaee7.batch

import java.util.concurrent.TimeUnit
import javax.batch.runtime.{BatchRuntime, BatchStatus}

import org.jberet.runtime.JobExecutionImpl
import org.scalatest.{FunSpec, Matchers}

class PartitionedBatchSpec extends FunSpec with Matchers {
  describe("Partitioned Batch Spec") {
    val jobOperator = BatchRuntime.getJobOperator

    it("Trace Partitioned Batchlet") {
      val jobId = "trace-partition-batchlet"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
    }

    it("Trace Partitioned Batchlet, Reducer Only") {
      val jobId = "trace-partition-batchlet-reducer-only"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
    }

    it("Trace Partitioned Chunk") {
      val jobId = "trace-partition-chunk"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
    }

    it("Trace Partitioned Chunk, Reducer Only") {
      val jobId = "trace-partition-chunk-reducer-only"
      val properties = new java.util.Properties

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
    }
  }
}
