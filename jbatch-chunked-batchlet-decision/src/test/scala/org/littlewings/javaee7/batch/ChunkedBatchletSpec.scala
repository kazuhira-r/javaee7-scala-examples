package org.littlewings.javaee7.batch

import java.nio.file.{Files, Paths}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
import javax.batch.runtime.{BatchRuntime, BatchStatus}

import org.jberet.runtime.JobExecutionImpl
import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

class ChunkedBatchletSpec extends FunSpec with Matchers {
  describe("Chunked - Batchlet Spec") {
    val jobOperator = BatchRuntime.getJobOperator

    it("using Properties, Success") {
      val jobId = "properties-job"
      val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
      val outputCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv")
      val outputArchiveCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv.gz")

      val properties = new java.util.Properties

      properties.put("csvFilePath", outputCsvFilePath.toString)
      properties.put("archiveCsvFilePath", outputArchiveCsvFilePath.toString)
      properties.put("writeStepStatus", "SUCCESS")

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
      jobExecution.getExitStatus should be("BATCHLET-SUCCESS")

      outputCsvFilePath.toFile should exist
      outputArchiveCsvFilePath.toFile should exist

      val source = Source.fromInputStream(new GZIPInputStream(Files.newInputStream(outputArchiveCsvFilePath)), "UTF-8")
      source.getLines.mkString(",") should be("Java,Scala,Groovy,Clojure,Kotlin,Perl,Ruby,Python,PHP,C")
      source.close()

      Files.delete(outputCsvFilePath)
      Files.delete(outputArchiveCsvFilePath)
    }

    it("using Properties, Fail") {
      val jobId = "properties-job"
      val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
      val outputCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv")
      val outputArchiveCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv.gz")

      val properties = new java.util.Properties

      properties.put("csvFilePath", outputCsvFilePath.toString)
      properties.put("archiveCsvFilePath", outputArchiveCsvFilePath.toString)
      properties.put("writeStepStatus", "PARAMETER-STATUS-FAIL")

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.FAILED)
      jobExecution.getExitStatus should be("DECISION-FAILED-END")

      outputCsvFilePath.toFile should exist
      outputArchiveCsvFilePath.toFile should not be (exist)

      Files.delete(outputCsvFilePath)
    }

    it("using UserData, Success") {
      val jobId = "user-data-job"
      val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
      val outputArchiveCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv.gz")

      val properties = new java.util.Properties

      properties.put("archiveCsvFilePath", outputArchiveCsvFilePath.toString)
      properties.put("writeStepStatus", "SUCCESS")

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.COMPLETED)
      jobExecution.getExitStatus should be("BATCHLET-SUCCESS")

      outputArchiveCsvFilePath.toFile should exist

      val source = Source.fromInputStream(new GZIPInputStream(Files.newInputStream(outputArchiveCsvFilePath)), "UTF-8")
      source.getLines.mkString(",") should be("Java,Scala,Groovy,Clojure,Kotlin,Perl,Ruby,Python,PHP,C")
      source.close()

      Files.delete(outputArchiveCsvFilePath)
    }

    it("using UserData, Fail") {
      val jobId = "user-data-job"
      val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
      val outputArchiveCsvFilePath = Paths.get(s"./target/languages-using-propeties-${now}.csv.gz")

      val properties = new java.util.Properties

      properties.put("archiveCsvFilePath", outputArchiveCsvFilePath.toString)
      properties.put("writeStepStatus", "PARAMETER-STATUS-FAIL")

      val executionId = jobOperator.start(jobId, properties)

      val jobExecution = jobOperator.getJobExecution(executionId)
      jobExecution.asInstanceOf[JobExecutionImpl].awaitTermination(0, TimeUnit.SECONDS)

      jobExecution.getBatchStatus should be(BatchStatus.FAILED)
      jobExecution.getExitStatus should be("DECISION-FAILED-END")

      outputArchiveCsvFilePath.toFile should not be (exist)
    }
  }
}
