package org.littlewings.javaee7.config

import org.apache.deltaspike.core.api.config.PropertyFileConfig

class MyApplicationPropertyFileConfig extends PropertyFileConfig {
  override def getPropertyFileName: String = "my-application.properties"

  override def isOptional: Boolean = false
}
