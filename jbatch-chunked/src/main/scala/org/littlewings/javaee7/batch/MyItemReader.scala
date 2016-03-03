package org.littlewings.javaee7.batch

import java.io.{BufferedReader, Serializable}
import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Files}
import javax.batch.api.BatchProperty
import javax.batch.api.chunk.ItemReader
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

import org.jboss.logging.Logger

@Dependent
@Named("MyItemReader")
class MyItemReader extends ItemReader {
  private val logger: Logger = Logger.getLogger(getClass)

  @Inject
  @BatchProperty
  private var filePath: String = _

  private var reader: BufferedReader = _

  override def open(checkpoint: Serializable): Unit = {
    logger.infof("Input File = %s.", filePath)
    reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)
  }

  override def readItem(): AnyRef = reader.readLine()

  override def checkpointInfo(): Serializable = {
    logger.infof("checkpoint.")
    null
  }

  override def close(): Unit = reader.close()
}
