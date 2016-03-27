import sbt.Keys._

name := "jta-implementations-standalone-jpa"

val projectScalaVersion = "2.11.8"

scalaVersion := projectScalaVersion

parallelExecution in Test := false

lazy val commonSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  organization := "org.littlewings",
  scalaVersion := projectScalaVersion,
  scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature"),
  updateOptions := updateOptions.value.withCachedResolution(true),
  parallelExecution in Test := false,
  fork in Test := true
)

lazy val root = (project in file("."))
  .aggregate(entity, bitronix, atomikos, jotm)

lazy val entity = (project in file("entity"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      // JPA
      "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
      // H2 Database
      "com.h2database" % "h2" % "1.4.191",
      // Test
      "org.scalatest" %% "scalatest" % "2.2.6" % "test"
    )
  )

lazy val bitronix = (project in file("bitronix"))
  .dependsOn(entity % "test->test")
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      // Bitronix JTA
      "org.codehaus.btm" % "btm" % "2.1.4"
    )
  )

lazy val atomikos = (project in file("atomikos"))
  .dependsOn(entity % "test->test")
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      // Atomikos JTA
      "com.atomikos" % "transactions-jta" % "4.0.2",
      "com.atomikos" % "transactions-jdbc" % "4.0.2",
      "com.atomikos" % "transactions-hibernate4" % "4.0.2",
      // JNDI Server
      "jboss" % "jnpserver" % "4.2.2.GA"
    )
  )

lazy val jotm = (project in file("jotm"))
  .dependsOn(entity % "test->test")
  .settings(commonSettings: _*)
  .settings(
    resolvers += "ow2 repository" at "http://repository.ow2.org/nexus/content/repositories/ow2-legacy",
    libraryDependencies ++= Seq(
      // JOTM JTA
      "org.ow2.jotm" % "jotm-core" % "2.2.3",
      "org.ow2.spec.ee" % "ow2-connector-1.5-spec" % "1.0.8" % "runtime",
      // XA DataSource
      "com.experlog" % "xapool" % "1.5.0"
    )
  )
