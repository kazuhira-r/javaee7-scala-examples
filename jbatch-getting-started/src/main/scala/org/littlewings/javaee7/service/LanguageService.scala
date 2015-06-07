package org.littlewings.javaee7.service

import javax.enterprise.context.ApplicationScoped
import javax.persistence.{EntityManager, PersistenceContext}

import org.littlewings.javaee7.entity.Language

import scala.collection.JavaConverters._

@ApplicationScoped
class LanguageService {
  @PersistenceContext
  private var entityManager: EntityManager = _

  def findById(id: Long): Language =
    entityManager.find(classOf[Language], id)

  def findAll: Seq[Language] = {
    val query =
      entityManager.createQuery("SELECT l FROM Language l ORDER BY l.id", classOf[Language])
    query.getResultList.asScala
  }
}
