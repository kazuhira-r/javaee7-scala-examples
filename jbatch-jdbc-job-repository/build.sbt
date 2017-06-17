name := "jbatch-jdbc-job-repository"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.12.1"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

libraryDependencies ++= Seq(
  // JBeret SE
  "org.jberet" % "jberet-se" % "1.2.3.Final" % Compile,
  "org.jboss.spec.javax.batch" % "jboss-batch-api_1.0_spec" % "1.0.0.Final" % Compile,
  "org.jboss.spec.javax.transaction" % "jboss-transaction-api_1.2_spec" % "1.0.0.Final" % Runtime,
  "javax.enterprise" % "cdi-api" % "1.1" % Compile,
  "javax.inject" % "javax.inject" % "1" % Compile,
  "org.jboss.weld.se" % "weld-se" % "2.4.4.Final" % Runtime,
  "org.wildfly.security" % "wildfly-security-manager" % "1.1.2.Final" % Runtime,
  "org.jboss.marshalling" % "jboss-marshalling" % "1.4.11.Final" % Runtime,
  "org.jboss.logging" % "jboss-logging" % "3.3.1.Final" % Compile,
  "org.jboss" % "jandex" % "2.0.3.Final" % Runtime,
  "com.google.guava" % "guava" % "18.0" % Runtime,

  // JDBC Driver for Job Repository
  "mysql" % "mysql-connector-java" % "5.1.42" % Runtime,

  // for Test
  "org.scalatest" %% "scalatest" % "3.0.3" % Test
)
