name := "jbatch-partitioned-tracing"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

parallelExecution in Test := false

javaOptions in Test += "-javaagent:/usr/local/byteman/current/lib/byteman.jar=script:trace-tx.btm"

libraryDependencies ++= Seq(
  // jBatch
  "org.jboss.spec.javax.batch" % "jboss-batch-api_1.0_spec" % "1.0.0.Final",
  "org.jberet" % "jberet-se" % "1.2.0.Final",
  "org.jboss.spec.javax.transaction" % "jboss-transaction-api_1.2_spec" % "1.0.0.Final" % "runtime",
  "org.jboss.weld.se" % "weld-se" % "2.3.3.Final" % "runtime",
  "org.jboss" % "jandex" % "2.0.2.Final" % "runtime",
  "org.wildfly.security" % "wildfly-security-manager" % "1.1.2.Final" % "runtime",
  "org.jboss.marshalling" % "jboss-marshalling" % "1.4.10.Final" % "runtime",

  // Logging
  "org.jboss.logging" % "jboss-logging" % "3.3.0.Final",

  // Test
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
