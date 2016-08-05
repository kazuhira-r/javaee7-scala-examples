package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default

import org.apache.deltaspike.core.api.exclude.Exclude
import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.littlewings.javaee7.projectstage.MyProjectStage

trait GreetingService {
  def greet: String
}

@ApplicationScoped
class DefaultGreetingService extends GreetingService {
  def greet: String = "Hello CDI!!"
}

@ApplicationScoped
@Exclude(ifProjectStage = Array(classOf[ProjectStage.Production], classOf[ProjectStage.Staging], classOf[MyProjectStage]))
class TestingGreetingService extends GreetingService {
  def greet: String = "Fot Test!!"
}
