package org.littlewings.javaee7.service

import scala.collection.JavaConverters._

import javax.enterprise.context.RequestScoped
import javax.inject.Inject

import org.apache.lucene.search.Query
import org.hibernate.search.jpa.FullTextEntityManager

import org.littlewings.javaee7.entity.Book

@RequestScoped
class BookSearchService {
  @Inject
  private var fullTextEm: FullTextEntityManager = _

  def indexing(): Unit =
    fullTextEm.createIndexer().startAndWait()

  def search(title: String, summary: String): (Query, Iterable[Book]) = {
    val queryBuilder =
      fullTextEm
        .getSearchFactory
        .buildQueryBuilder
        .forEntity(classOf[Book])
        .get

    val queries =
      List(
        Option(title).map { t =>
          queryBuilder
            .keyword
            .onField("title")
            .matching(t)
            .createQuery
        },
        Option(summary).map { s =>
          queryBuilder
            .keyword
            .onField("summary")
            .matching(s)
            .createQuery
        }
      ).flatten

    val booleanJunction = queryBuilder.bool
    val luceneQuery =
      queries match {
        case titleQuery :: summaryQuery :: Nil =>
          booleanJunction.should(titleQuery).should(summaryQuery).createQuery
        case query :: Nil =>
          booleanJunction.should(query).createQuery
        case _ =>
          throw new IllegalArgumentException(s"Unknown Query[${queries.toString}")
      }

    (luceneQuery,
     fullTextEm
       .createFullTextQuery(luceneQuery, classOf[Book])
       .getResultList
       .asScala
       .asInstanceOf[Iterable[Book]])
  }
}
