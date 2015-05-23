name := "resteasy-client-jackson2-scala"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

updateOptions := updateOptions.value.withCachedResolution(true)

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "org.jboss.resteasy" % "resteasy-client" % "3.0.11.Final",
  "org.jboss.resteasy" % "resteasy-jackson2-provider" % "3.0.11.Final",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.5.2",

  "org.jboss.resteasy" % "resteasy-jdk-http" % "3.0.11.Final" % "test",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
