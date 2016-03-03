package org.littlewings.javaee7.batch

import java.io.Serializable
import javax.batch.api.chunk.ItemWriter
import javax.enterprise.context.Dependent
import javax.inject.Named
import javax.persistence.Persistence

import org.jboss.logging.Logger

import scala.collection.JavaConverters._
import scala.collection.mutable

@Dependent
@Named("MyItemWriter")
class MyItemWriter extends ItemWriter {
  private val logger: Logger = Logger.getLogger(getClass)

  private val store: mutable.Map[String, Word] =
    new mutable.HashMap().withDefault(t => Word(t, 0))

  override def open(checkpoint: Serializable): Unit =
    logger.infof("open writer.")

  override def writeItems(items: java.util.List[AnyRef]): Unit = {
    logger.infof(s"write items[${items.size}].")

    items
      .asScala
      .foreach {
        case tokens: Seq[_] => tokens.foreach {
          case token: String => store += (token -> Word(token, store(token).count + 1))
        }
      }
  }

  override def checkpointInfo(): Serializable = {
    logger.infof("checkpoint.")
    null
  }

  override def close(): Unit = {
    val emf = Persistence.createEntityManagerFactory("javaee7.batch.pu")
    val em = emf.createEntityManager

    val tx = em.getTransaction
    tx.begin()
    store.values.foreach(em.persist)
    tx.commit()

    em.close()
    emf.close()

    logger.infof("close.")
  }
}
