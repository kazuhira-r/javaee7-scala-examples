name := "reseteasy-html-template"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

incOptions := incOptions.value.withNameHashing(true)

jetty()

artifactName := { (version: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  //artifact.name + "." + artifact.extension
  "javaee7-web." + artifact.extension
}

val jettyVersion = "9.2.2.v20140723"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "javax" % "javaee-web-api" % "7.0" % "provided",
  "org.jboss.resteasy" % "resteasy-html" % "3.0.8.Final" exclude("org.jboss.resteasy", "resteasy-jaxrs"),
  "org.apache.velocity" % "velocity" % "1.7",
  "org.apache.velocity" % "velocity-tools" % "2.0" exclude("javax.servlet", "servlet-api"),
  "com.gilt" %% "handlebars-scala" % "2.0.1",
  "com.jsuereth" %% "scala-arm" % "1.4"
)
