name := "undertow-websocket"

lazy val commonSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  organization := "org.littlewings",
  scalaVersion := "2.12.1",
  scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature"),
  updateOptions := updateOptions.value.withCachedResolution(true)
)

lazy val server = (project in file("server")).
  settings(
    name := "undertow-websocket-server",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-websockets-jsr" % "1.4.10.Final"
    )
  )

lazy val client = (project in file("client")).
  settings(
    name := "undertow-websocket-client",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-websockets-jsr" % "1.4.10.Final"
    )
  )
