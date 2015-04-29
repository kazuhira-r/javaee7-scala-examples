name := "arquillian-tomcat-embedded-8"

version := "1.0"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

libraryDependencies ++= Seq(
  "org.jboss.resteasy" % "resteasy-servlet-initializer" % "3.0.11.Final",
  "org.jboss.resteasy" % "resteasy-cdi" % "3.0.11.Final",
  "org.jboss.weld.servlet" % "weld-servlet" % "2.2.11.Final",
  "org.jboss.arquillian.container" % "arquillian-tomcat-embedded-8" % "1.0.0.CR7" % "test",
  "org.jboss.arquillian.junit" % "arquillian-junit-container" % "1.1.8.Final" % "test",
  "org.apache.tomcat.embed" % "tomcat-embed-core" % "8.0.21" % "provided",
  "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "8.0.21" % "provided",
  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % "8.0.21" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test"
)
