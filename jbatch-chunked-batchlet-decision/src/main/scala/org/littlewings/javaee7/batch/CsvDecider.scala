package org.littlewings.javaee7.batch

import javax.batch.api.Decider
import javax.batch.runtime.StepExecution
import javax.enterprise.context.Dependent
import javax.inject.Named

@Dependent
@Named
class CsvDecider extends Decider {
  override def decide(executions: Array[StepExecution]): String = {
    executions.foreach(_.getExitStatus)
    if (executions.filterNot(_.getExitStatus == "SUCCESS").isEmpty) "DECISION-SUCCESS"
    else "DECISION-ERROR"
  }
}
