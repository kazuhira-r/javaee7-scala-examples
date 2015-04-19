name := "arquillian-wildfly-container-embedded"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-Xlint", "-unchecked", "-deprecation", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

fork in Test := true

javaOptions ++= Seq("-Djava.util.logging.manager=org.jboss.logmanager.LogManager")  // embedded

resolvers += "JBoss Public Maven Repository Group" at "https://repository.jboss.org/nexus/content/groups/public/"

libraryDependencies ++= Seq(
  "javax" % "javaee-web-api" % "7.0" % "provided",
  "org.jboss.arquillian.junit" % "arquillian-junit-container" % "1.1.8.Final" % "test",
  "org.jboss.shrinkwrap.resolver" % "shrinkwrap-resolver-depchain" % "2.2.0-beta-2" % "test",
  "org.wildfly" % "wildfly-arquillian-container-embedded" % "8.2.0.Final" % "test",  // embedded
  // "org.wildfly" % "wildfly-arquillian-container-managed" % "8.2.0.Final" % "test",  // managed
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test"
)
