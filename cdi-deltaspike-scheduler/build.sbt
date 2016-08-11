name := "cdi-deltaspike-scheduler"

organization := "org.littlewings"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

enablePlugins(JettyPlugin)

webappWebInfClasses := true

artifactName := {
  (scalaVersion: ScalaVersion, module: ModuleID, artifact: Artifact) =>
    // artifact.name + "." + artifact.extension
    "ROOT." + artifact.extension
}

fork in Test := true

libraryDependencies ++= Seq(
  "javax" % "javaee-web-api" % "7.0" % Provided,
  "org.apache.deltaspike.core" % "deltaspike-core-api" % "1.7.1" % Compile,
  "org.apache.deltaspike.core" % "deltaspike-core-impl" % "1.7.1" % Runtime,
  "org.apache.deltaspike.modules" % "deltaspike-scheduler-module-api" % "1.7.1" % Compile,
  "org.apache.deltaspike.modules" % "deltaspike-scheduler-module-impl" % "1.7.1" % Runtime,
  "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-api" % "1.7.1" % Compile,
  "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-weld" % "1.7.1" % Runtime,
  "org.quartz-scheduler" % "quartz" % "2.2.1" % Compile
)
