package org.littlewings.javaee7.cdi

import javax.enterprise.context.{ApplicationScoped, Dependent, RequestScoped, SessionScoped}

@ApplicationScoped
class ApplicationScopedService {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@SessionScoped
@SerialVersionUID(1L)
class SessionScopedService extends Serializable {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@RequestScoped
class RequestScopedService {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@Dependent
@SerialVersionUID(1L)
class DependentService extends Serializable {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}
