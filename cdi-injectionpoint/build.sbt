name := "cdi-injectionpoint"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.weld.se" % "weld-se" % "2.2.12.Final",
  "org.jboss.logging" % "jboss-logging" % "3.3.0.Final",
  "org.slf4j" % "slf4j-api" % "1.7.12",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
