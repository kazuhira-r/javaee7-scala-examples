name := "cdi-event"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.weld.se" % "weld-se" % "2.2.12.Final",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
