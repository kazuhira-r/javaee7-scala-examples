name := "bean-validation-group-conversion"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

val tomcatVersion = "8.0.23"
val resteasyVersion = "3.0.11.Final"
val weldServletVersion = "2.2.13.Final"
val scalaTestVersion = "2.2.5"

libraryDependencies ++= Seq(
  "org.apache.tomcat.embed" % "tomcat-embed-core" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-jasper" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % tomcatVersion,
  "org.jboss.resteasy" % "resteasy-servlet-initializer" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-cdi" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-validator-provider-11" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-jackson2-provider" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-client" % resteasyVersion % "test",
  "org.jboss.weld.servlet" % "weld-servlet" % weldServletVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
