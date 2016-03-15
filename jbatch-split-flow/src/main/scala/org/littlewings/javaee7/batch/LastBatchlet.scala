package org.littlewings.javaee7.batch

import javax.batch.api.AbstractBatchlet
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.{Inject, Named}

@Dependent
@Named
class LastBatchlet extends AbstractBatchlet {
  @Inject
  var jobContext: JobContext = _

  override def process(): String = {
    jobContext.setExitStatus("JOB-FINISH")

    "FINISH"
  }
}
