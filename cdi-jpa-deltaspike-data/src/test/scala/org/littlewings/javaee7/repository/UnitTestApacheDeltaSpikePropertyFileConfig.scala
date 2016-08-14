package org.littlewings.javaee7.repository

import org.apache.deltaspike.core.api.config.PropertyFileConfig

class UnitTestApacheDeltaSpikePropertyFileConfig extends PropertyFileConfig {
  override def getPropertyFileName: String = "META-INF/unit-test-apache-deltaspike.properties"

  override def isOptional: Boolean = false
}
