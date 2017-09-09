name := "resteasy-customize-http-engine"

organization := "org.littlewings"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.3"

updateOptions := updateOptions.value.withCachedResolution(true)

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  // for RESTEasy Client
  "org.jboss.resteasy" % "resteasy-client" % "3.1.4.Final" % Compile exclude("junit", "junit"),
  "org.jboss.resteasy" % "resteasy-jackson2-provider" % "3.1.4.Final" % Compile,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.9" % Compile,

  // dependency retrieve
  "org.jboss.resteasy" % "resteasy-jaxrs" % "3.1.4.Final" % Compile,
  "org.jboss.logging" % "jboss-logging" % "3.3.0.Final" % Compile,
  "org.jboss.logging" % "jboss-logging-annotations" % "2.0.1.Final" % Compile,
  "org.jboss.logging" % "jboss-logging-processor" % "2.0.1.Final" % Compile,
  "org.apache.httpcomponents" % "httpclient" % "4.5.2" % Compile,
  "com.fasterxml.jackson.core" % "jackson-core" % "2.8.9" % Compile,
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.8.9" % Compile,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.9" % Compile,
  "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-json-provider" % "2.8.9" % Compile,
  "javax.activation" % "activation" % "1.1.1" % Compile,
  "org.jboss.spec.javax.servlet" % "jboss-servlet-api_3.1_spec" % "1.0.0.Final" % Compile,
  "org.jboss.spec.javax.annotation" % "jboss-annotations-api_1.2_spec" % "1.0.0.Final" % Compile,
  "org.jboss.spec.javax.ws.rs" % "jboss-jaxrs-api_2.0_spec" % "1.0.1.Beta1" % Compile,
  "commons-io" % "commons-io" % "2.5" % Compile,
  "net.jcip" % "jcip-annotations" % "1.0" % Compile,

  // for Customize HttpEngine
  "com.squareup.okhttp3" % "okhttp" % "3.9.0" % Compile,

  // for test
  "org.jboss.resteasy" % "resteasy-netty4" % "3.1.4.Final" % Test,
  "io.netty" % "netty-all" % "4.1.15.Final" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)
