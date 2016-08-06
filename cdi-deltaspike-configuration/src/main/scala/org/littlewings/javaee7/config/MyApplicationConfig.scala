package org.littlewings.javaee7.config

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.core.api.config.ConfigProperty

@ApplicationScoped
class MyApplicationConfig {
  @Inject
  @ConfigProperty(name = "application.name")
  var applicationName: String = _
}
