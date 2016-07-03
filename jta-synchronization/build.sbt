name := "jta-synchronization"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  // Nayarana JTA
  "org.jboss.spec.javax.transaction" % "jboss-transaction-api_1.2_spec" % "1.0.1.Final" % "runtime",
  "org.jboss.narayana.jta" % "narayana-jta" % "5.3.3.Final",

  // JNDI Server
  "jboss" % "jnpserver" % "4.2.2.GA",
  "org.jboss.logging" % "jboss-logging" % "3.3.0.Final",

  // JPA
  "org.hibernate" % "hibernate-entitymanager" % "5.2.1.Final",
  "dom4j" % "dom4j" % "1.6.1",

  // H2 Database
  "com.h2database" % "h2" % "1.4.192",

  // Test
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
