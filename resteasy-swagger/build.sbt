name := "resteasy-swagger"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.12.1"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.jboss.resteasy" % "resteasy-undertow" % "3.0.19.Final",
  "org.jboss.resteasy" % "resteasy-jackson2-provider" % "3.0.19.Final",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.6",
  "io.undertow" % "undertow-core" % "1.4.10.Final",
  "io.undertow" % "undertow-servlet" % "1.4.10.Final",
  "io.swagger" % "swagger-jaxrs" % "1.5.12" exclude("javax.ws.rs", "jsr311-api")
)
