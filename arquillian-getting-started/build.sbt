name := "arquillian-getting-started"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.2"

organization := "org.littlewings"

scalacOptions ++= Seq("-Xlint", "-unchecked", "-deprecation", "-feature")

incOptions := incOptions.value.withNameHashing(true)

fork in Test := true

parallelExecution in Test := false

jetty()

artifactName := { (version: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  //artifact.name + "." + artifact.extension
  "javaee7-web." + artifact.extension
}

makePomConfiguration := makePomConfiguration.value.copy(file = new File("pom.xml"))

resolvers += "JBoss Public Maven Repository Group" at "https://repository.jboss.org/nexus/content/groups/public/"

// Managedの時は必要
// envVars in Test += ("JBOSS_HOME", "../wildfly-8.1.0.Final")

val jettyVersion = "9.2.2.v20140723"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "org.jboss.spec" % "jboss-javaee-web-7.0" % "1.0.1.Final" % "provided",
  // "org.wildfly" % "wildfly-arquillian-container-managed" % "8.1.0.Final" % "test",  // Managed
  "org.wildfly" % "wildfly-arquillian-container-remote" % "8.1.0.Final" % "test",  // Remote
  "org.jboss.arquillian.protocol" % "arquillian-protocol-servlet" % "1.1.5.Final" % "test",  // Remote
  "org.jboss.arquillian.junit" % "arquillian-junit-container" % "1.1.5.Final" % "test",
  "org.jboss.shrinkwrap.resolver" % "shrinkwrap-resolver-depchain" % "2.2.0-alpha-2" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.jboss.resteasy" % "resteasy-client" % "3.0.8.Final" % "test",  // テストでのHTTPリクエスト実行に使用
  "org.apache.commons" % "commons-lang3" % "3.3.2"  // ShrinkWrap Maven Resolverのサンプル的に使用
)
