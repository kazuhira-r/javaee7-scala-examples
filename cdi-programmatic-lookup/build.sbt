name := "cdi-programmatic-lookup"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

val tomcatVersion = "8.0.22"
val resteasyVersion = "3.0.11.Final"
val weldServletVersion = "2.2.12.Final"
val scalaTestVersion = "2.2.5"

libraryDependencies ++= Seq(
  "org.apache.tomcat.embed" % "tomcat-embed-core" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-jasper" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % tomcatVersion,
  "org.jboss.resteasy" % "resteasy-servlet-initializer" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-cdi" % resteasyVersion,
  "org.jboss.weld.servlet" % "weld-servlet" % weldServletVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
