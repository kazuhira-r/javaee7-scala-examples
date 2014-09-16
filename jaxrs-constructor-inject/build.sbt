name := "jaxrs-constructor-inject"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature", "-Xprint:jvm")

incOptions := incOptions.value.withNameHashing(true)

jetty()

artifactName := { (version: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  //artifact.name + "." + artifact.extension
  "javaee7-web." + artifact.extension
}

val jettyVersion = "9.2.3.v20140905"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "javax" % "javaee-web-api" % "7.0" % "provided"
)
