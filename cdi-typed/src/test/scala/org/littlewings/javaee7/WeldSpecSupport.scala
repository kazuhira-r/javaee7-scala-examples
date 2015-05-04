package org.littlewings.javaee7

import org.jboss.weld.environment.se.Weld
import org.scalatest.Suite

trait WeldSpecSupport extends Suite {
  protected def withWeld(f: => Unit): Unit = {
    val weld = new Weld

    try {
      weld.initialize()

      f
    } finally {
      weld.shutdown()
    }
  }
}
