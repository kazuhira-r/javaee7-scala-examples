name := "hibernate-search-with-jpa"

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
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "javax" % "javaee-web-api" % "7.0" % "provided",
  "org.hibernate" % "hibernate-search-orm" % "4.5.0.Final" % "provided",
  "org.apache.lucene" % "lucene-kuromoji" % "3.6.2" excludeAll(
    ExclusionRule(organization = "org.apache.lucene", name = "lucene-core"),
    ExclusionRule(organization = "org.apache.lucene", name = "lucene-analyzers")
  )
)
}
