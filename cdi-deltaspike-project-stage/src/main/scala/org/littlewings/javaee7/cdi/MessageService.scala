package org.littlewings.javaee7.cdi

import javax.enterprise.context.ApplicationScoped

import org.apache.deltaspike.core.api.exclude.Exclude
import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.littlewings.javaee7.projectstage.{MyProjectStage, MyProjectStageHolder}

trait MessageService {
  def get: String
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.Production]))
class ProductionMessageService extends MessageService {
  override def get: String = "ProductionStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.SystemTest]))
class SystemTestMessageService extends MessageService {
  override def get: String = "SystemTestStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.Staging]))
class StagingMessageService extends MessageService {
  override def get: String = "StagingStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.Development]))
class DevelopmentMessageService extends MessageService {
  override def get: String = "DevelopmentStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.IntegrationTest]))
class IntegrationTestMessageService extends MessageService {
  override def get: String = "IntegrationTestStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.UnitTest]))
class UnitTestMessageService extends MessageService {
  override def get: String = "UnitTestStage!!"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[MyProjectStage]))
class MyProjectStageMessageService extends MessageService {
  override def get: String = "MyProjectStage!!"
}
