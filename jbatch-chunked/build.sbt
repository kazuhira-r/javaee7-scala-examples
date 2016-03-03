name := "jbatch-chunked"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  // jBatch
  "org.jboss.spec.javax.batch" % "jboss-batch-api_1.0_spec" % "1.0.0.Final",
  "org.jberet" % "jberet-se" % "1.2.0.Final",
  "org.jboss.spec.javax.transaction" % "jboss-transaction-api_1.2_spec" % "1.0.0.Final" % "runtime",
  "org.jboss.weld.se" % "weld-se" % "2.3.3.Final" % "runtime",
  // "org.jboss" % "jandex" % "2.0.2.Final" % "runtime",
  "org.wildfly.security" % "wildfly-security-manager" % "1.1.2.Final" % "runtime",
  "org.jboss.marshalling" % "jboss-marshalling" % "1.4.10.Final" % "runtime",

  // JPA
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  // "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" exclude("org.jboss", "jandex"),

  // Lucene Kuromoji
  "org.apache.lucene" % "lucene-analyzers-kuromoji" % "5.5.0",

  // H2 Database
  "com.h2database" % "h2" % "1.4.191",

  // Test
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
