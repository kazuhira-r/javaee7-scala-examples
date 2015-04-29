name := "embedded-tomcat-jaxrs-cdi"

version := "0.0.1-SNAPSHOT"

organization := "org.littlewings"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

fork in run := true

connectInput := true

val tomcatVersion = "8.0.20"
val resteasyVersion = "3.0.10.Final"

libraryDependencies ++= Seq(
  "org.apache.tomcat.embed" % "tomcat-embed-core" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-jasper" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % tomcatVersion,
  "org.jboss.resteasy" % "resteasy-servlet-initializer" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-cdi" % resteasyVersion,
  "org.jboss.weld.servlet" % "weld-servlet" % "2.2.9.Final"
)
