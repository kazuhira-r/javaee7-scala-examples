name := "cdi-interceptor-apply-point"

version := "1.0"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-unchecked", "-deprecation", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.weld.se" % "weld-se" % "2.2.11.Final",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
