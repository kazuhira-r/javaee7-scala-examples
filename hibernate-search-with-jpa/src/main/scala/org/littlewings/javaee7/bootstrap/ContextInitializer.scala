package org.littlewings.javaee7.bootstrap

import javax.annotation.PostConstruct
import javax.ejb.{Singleton, Startup}
import javax.persistence.{EntityManager, PersistenceContext}

import org.hibernate.search.jpa.Search

@Singleton
@Startup
class ContextInitializer {
  @PersistenceContext
  private var em: EntityManager = _

  @PostConstruct
  def initialize(): Unit = {
    /* インデックスの保存先をInfinispanにして、
     * かつクラスタにする場合はこのコードは外す
    val fullTextEm = Search.getFullTextEntityManager(em)
    fullTextEm.createIndexer().purgeAllOnStart(true).startAndWait()
     */
  }
}
