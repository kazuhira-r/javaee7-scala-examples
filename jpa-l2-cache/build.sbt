name := "jpa-l2-cache"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

organization := "org.lilttlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked")

seq(webSettings: _*)

artifactName := { (version: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  //artifact.name + "." + artifact.extension
  "javaee7-web." + artifact.extension
}

{
val jettyVersion = "9.1.2.v20140210"
libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion% "container",
  "javax" % "javaee-web-api" % "7.0" % "provided"
)
}
