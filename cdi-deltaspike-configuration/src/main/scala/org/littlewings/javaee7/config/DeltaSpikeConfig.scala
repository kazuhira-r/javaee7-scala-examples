package org.littlewings.javaee7.config

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.core.api.config.ConfigProperty

@ApplicationScoped
class DeltaSpikeConfig {
  @Inject
  @ConfigProperty(name = "database.server")
  var databaseServer: String = _

  @Inject
  @ConfigProperty(name = "database.port")
  var databasePort: Int = _

  @Inject
  @ConfigProperty(name = "database.name")
  var databaseName: String = _

  @Inject
  @ConfigProperty(name = "missing.key", defaultValue = "defaultValue")
  var missingKey: String = _

  @Inject
  @ConfigProperty(name = "missing.key.without.default.value")
  var missingKeyWithoutDefaultValue: String = _

  @Inject
  @ConfigProperty(name = "jdbc.url")
  var jdbcUrl: String = _
}
