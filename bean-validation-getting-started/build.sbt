name := "bean-validation-getting-started"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked")

fork in Test := true

libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-validator" % "5.1.0.Final",
  "javax.el" % "javax.el-api" % "2.2.4",
  "org.glassfish.web" % "javax.el" % "2.2.4",
  "org.scalatest" %% "scalatest" % "2.1.2" % "test"
)
