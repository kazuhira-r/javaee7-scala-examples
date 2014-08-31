import sbt._
import sbt.Keys._

object BuildSettings {
  val buildOrganization = "org.littlewings"
  val buildVersion = "0.0.1-SNAPSHOT"
  val buildScalaVersion = "2.11.2"
  val buildScalacOptions = Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

  val buildSettings = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    scalacOptions ++= buildScalacOptions,
    incOptions := incOptions.value.withNameHashing(true)
  )
}

object RestEasyEmbedded extends Build {
  import BuildSettings._

  lazy val root =
    Project("resteasy-embedded",
            file("."),
            settings = buildSettings)
              .aggregate(jdkHttpServer, undertow)

  lazy val jdkHttpServer =
    Project("resteasy-embedded-jdkhttpserver",
            file("resteasy-embedded-jdkhttpserver"),
            settings = buildSettings
                      ++ Seq(libraryDependencies += "org.jboss.resteasy" % "resteasy-jdk-http" % "3.0.8.Final"))

  lazy val undertow =
    Project("resteasy-embedded-undertow",
            file("resteasy-embedded-undertow"),
            settings = buildSettings
                      ++ Seq(fork in run := true)  // Undertowの場合は、これが必要
                      ++ Seq(connectInput := true)
                      ++ Seq(libraryDependencies ++= Seq("org.jboss.resteasy" % "resteasy-undertow" % "3.0.8.Final",
                                                         "io.undertow" % "undertow-core" % "1.0.15.Final",
                                                         "io.undertow" % "undertow-servlet" % "1.0.15.Final")))
}
