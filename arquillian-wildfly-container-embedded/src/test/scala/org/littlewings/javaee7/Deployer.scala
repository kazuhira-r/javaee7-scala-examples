package org.littlewings.javaee7

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven

trait Deployer {
  @Deployment
  def createDeployment: WebArchive =
   ShrinkWrap
     .create(classOf[WebArchive])
     .addPackages(true, "org.littlewings.javaee7")
     .addAsLibraries {
       Maven
         .resolver
         .resolve("org.scala-lang:scala-library:2.11.6")
         .withTransitivity
         .asFile: _*
     }
     .addAsLibraries {
       Maven
         .resolver
         .resolve("org.scalatest:scalatest_2.11:2.2.4")
         .withTransitivity
         .asFile: _*
     }
}
