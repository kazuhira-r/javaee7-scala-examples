import AssemblyKeys._

name := "jersey-standalone-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

incOptions := incOptions.value.withNameHashing(true)

assemblySettings

// jarName in assembly := s"${name.value}-${version.value}.jar"

mainClass in assembly := Some("app.Main")

libraryDependencies += "org.glassfish.jersey.containers" % "jersey-container-jdk-http" % "2.10.1"
