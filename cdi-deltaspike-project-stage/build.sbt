name := "cdi-deltaspike-project-stage"

version := "0.0.1-SNAPSHOT"

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
  "org.apache.deltaspike.modules" % "deltaspike-test-control-module-api" % "1.7.1" % Test,
  "org.apache.deltaspike.modules" % "deltaspike-test-control-module-impl" % "1.7.1" % Test,
  "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-weld" % "1.7.1" % Test,
  "org.jboss.weld.se" % "weld-se-core" % "2.3.5.Final" % Test,
  "org.scalatest" %% "scalatest" % "2.2.6" % Test,
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)
