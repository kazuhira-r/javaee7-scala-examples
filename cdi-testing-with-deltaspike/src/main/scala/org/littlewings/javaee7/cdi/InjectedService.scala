package org.littlewings.javaee7.cdi

import javax.enterprise.context.{ApplicationScoped, Dependent, RequestScoped, SessionScoped}
import javax.inject.Inject

@ApplicationScoped
class InjectedApplicationScopedService {
  @Inject
  private var applicationScopedService: ApplicationScopedService = _

  @Inject
  private var sessionScopedService: SessionScopedService = _

  @Inject
  private var requestScopedService: RequestScopedService = _

  @Inject
  private var dependentService: DependentService = _

  def say(): Unit = {
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
  }
}

@SessionScoped
@SerialVersionUID(1L)
class InjectedSessionScopedService extends Serializable {
  @Inject
  private var applicationScopedService: ApplicationScopedService = _

  @Inject
  private var sessionScopedService: SessionScopedService = _

  @Inject
  private var requestScopedService: RequestScopedService = _

  @Inject
  private var dependentService: DependentService = _

  def say(): Unit = {
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
  }
}

@RequestScoped
class InjectedRequestScopedService {
  @Inject
  private var applicationScopedService: ApplicationScopedService = _

  @Inject
  private var sessionScopedService: SessionScopedService = _

  @Inject
  private var requestScopedService: RequestScopedService = _

  @Inject
  private var dependentService: DependentService = _

  def say(): Unit = {
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
  }
}

@Dependent
@SerialVersionUID(1L)
class InjectedDependentService extends Serializable {
  @Inject
  private var applicationScopedService: ApplicationScopedService = _

  @Inject
  private var sessionScopedService: SessionScopedService = _

  @Inject
  private var requestScopedService: RequestScopedService = _

  @Inject
  private var dependentService: DependentService = _

  def say(): Unit = {
    applicationScopedService.say()
    sessionScopedService.say()
    requestScopedService.say()
    dependentService.say()
    println(s"Hello, ${getClass.getSimpleName}#${hashCode}")
  }
}
