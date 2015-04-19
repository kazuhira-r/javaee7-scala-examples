name := "cdi-alternative"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

val tomcatVersion = "8.0.21"
val resteasyVersion = "3.0.11.Final"
val weldServletVersion = "2.2.10.SP1"
val scalaTestVersion = "2.2.4"

libraryDependencies ++= Seq(
  "org.apache.tomcat" % "tomcat-catalina" % tomcatVersion,
  "org.apache.tomcat" % "tomcat-jasper" % tomcatVersion,
  "org.apache.tomcat.embed" % "tomcat-embed-core" % tomcatVersion,
  "org.jboss.resteasy" % "resteasy-servlet-initializer" % resteasyVersion,
  "org.jboss.resteasy" % "resteasy-cdi" % resteasyVersion,
  "org.jboss.weld.servlet" % "weld-servlet" % weldServletVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
