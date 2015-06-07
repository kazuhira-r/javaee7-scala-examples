package org.littlewings.javaee7.service

import javax.enterprise.context.ApplicationScoped
import javax.persistence.{EntityManager, Persistence}

import org.littlewings.javaee7.entity.Language

@ApplicationScoped
class LanguageService {
  private def entityManager: EntityManager =
    Persistence.createEntityManagerFactory("javaee7.web.pu").createEntityManager

  def findById(id: Long): Language =
    entityManager.find(classOf[Language], id)
}
