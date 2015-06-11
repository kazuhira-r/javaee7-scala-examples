name := "bean-validation-my-constraint"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint",  "-deprecation","-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-validator" % "5.1.3.Final",
  "javax.el" % "javax.el-api" % "2.2.4",
  "org.glassfish.web" % "javax.el" % "2.2.4",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)
