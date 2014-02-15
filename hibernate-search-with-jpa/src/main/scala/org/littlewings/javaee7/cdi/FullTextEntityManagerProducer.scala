package org.littlewings.javaee7.cdi

import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.persistence.{EntityManager, PersistenceContext}

import org.hibernate.search.jpa.{Search, FullTextEntityManager}

@Dependent
class FullTextEntityManagerProducer {
  @PersistenceContext
  private var em: EntityManager = _

  @Produces
  def createFullTextEntityManager: FullTextEntityManager =
    Search.getFullTextEntityManager(em)
}
