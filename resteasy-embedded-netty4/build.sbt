name := "resteasy-embedded-netty4"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.resteasy" % "resteasy-netty4" % "3.0.13.Final",
  "io.netty" % "netty-all" % "4.0.32.Final",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
