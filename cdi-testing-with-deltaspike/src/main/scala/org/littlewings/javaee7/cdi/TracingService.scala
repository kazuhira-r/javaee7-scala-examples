package org.littlewings.javaee7.cdi

import javax.enterprise.context.{ApplicationScoped, Dependent, RequestScoped, SessionScoped}

@EnableTracing
@ApplicationScoped
class TracingApplicationScopedService {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@EnableTracing
@SessionScoped
@SerialVersionUID(1L)
class TracingSessionScopedService extends Serializable {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@EnableTracing
@RequestScoped
class TracingRequestScopedService {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}

@EnableTracing
@Dependent
@SerialVersionUID(1L)
class TracingDependentService extends Serializable {
  def say(): Unit =
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
}
