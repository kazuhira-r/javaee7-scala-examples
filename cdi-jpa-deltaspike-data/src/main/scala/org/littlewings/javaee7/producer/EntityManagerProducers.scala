package org.littlewings.javaee7.producer

import javax.enterprise.context.{ApplicationScoped, Dependent}
import javax.enterprise.inject.Produces
import javax.persistence.{EntityManager, EntityManagerFactory, Persistence, PersistenceContext}

import org.apache.deltaspike.core.api.exclude.Exclude
import org.apache.deltaspike.core.api.projectstage.ProjectStage

object EntityManagerProducers {

  @Dependent
  @Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.IntegrationTest]))
  class IntegrationTestEntityManagerProducer {
    @PersistenceContext(unitName = "integration-test.persistence.unit")
    var entityManager: EntityManager = _

    @Produces
    @ApplicationScoped
    def entityManagerProducer: EntityManager = entityManager
  }

  @Dependent
  @Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.UnitTest]))
  class UnitTestEntityManagerProducer {
    @Produces
    @ApplicationScoped
    def entityManagerFactoryProducer: EntityManagerFactory = Persistence.createEntityManagerFactory("unit-test.persistence.unit")

    @Produces
    def entityManagerProducer(emf: EntityManagerFactory): EntityManager =
      emf.createEntityManager
  }

}
