name := "cdi-extension"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.weld.se" % "weld-se" % "2.2.10.SP1",
  "javax" % "javaee-web-api" % "7.0",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
