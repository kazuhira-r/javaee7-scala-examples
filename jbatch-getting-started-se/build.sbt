name := "jbatch-getting-started-se"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions := Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  // jBatch
  "org.jboss.spec.javax.batch" % "jboss-batch-api_1.0_spec" % "1.0.0.Final",
  "org.jberet" % "jberet-se" % "1.1.0.Final",
  "org.jboss.weld.se" % "weld-se" % "2.2.12.Final" % "runtime",
  "org.jboss" % "jandex" % "1.2.4.Final" % "runtime",
  "org.wildfly.security" % "wildfly-security-manager" % "1.1.2.Final" % "runtime",
  "org.jboss.marshalling" % "jboss-marshalling" % "1.4.10.Final" % "runtime",

  // JPA
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final" exclude("org.jboss", "jandex"),
  "mysql" % "mysql-connector-java" % "5.1.35" % "runtime",

  // logging
  "org.jboss.logging" % "jboss-logging" % "3.3.0.Final",
  "org.slf4j" % "slf4j-api" % "1.7.12",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)
