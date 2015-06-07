name := "jbatch-getting-started"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions := Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

jetty()

webInfClasses in webapp := true

artifactName := { (version: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  //artifact.name + "." + artifact.extension
  "javaee7-web." + artifact.extension
}

val jettyVersion = "9.2.11.v20150529"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "javax" % "javaee-api" % "7.0" % "provided",
  "org.jboss.logging" % "jboss-logging" % "3.1.4.GA" % "provided"
)
