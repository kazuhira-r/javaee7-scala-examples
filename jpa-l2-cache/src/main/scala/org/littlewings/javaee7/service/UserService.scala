package org.littlewings.javaee7.service

import scala.collection.JavaConverters._

import javax.enterprise.context.RequestScoped
import javax.persistence.{EntityManager, PersistenceContext}
import javax.transaction.Transactional

import org.littlewings.javaee7.entity.User

@Transactional
@RequestScoped
class UserService {
  @PersistenceContext
  private var em: EntityManager = _

  def create(user: User): Unit =
    em.persist(user)

  def update(user: User): User =
    em.merge(user)

  def remove(user: User): Unit =
    em.remove(em.merge(user))

  def findById(id: Int): User =
    em.find(classOf[User], id)

  def findAllOrderById: Iterable[User] =
    em
      .createQuery(s"""|SELECT u
                       |  FROM User u
                       | ORDER BY u.id ASC""".stripMargin)
      .setHint("org.hibernate.cacheable", true)
      .setHint("org.hibernate.cacheMode", "NORMAL")
      .getResultList
      .asScala
      .asInstanceOf[Iterable[User]]

  def findByOverAge(age: Int): Iterable[User] =
    em
      .createQuery(s"""|SELECT u
                       |  FROM User u
                       | WHERE u.age >= :age
                       | ORDER BY u.id ASC""".stripMargin)
      .setParameter("age", age)
      .setHint("org.hibernate.cacheable", true)
      .setHint("org.hibernate.cacheMode", "NORMAL")
      .getResultList
      .asScala
      .asInstanceOf[Iterable[User]]
}
