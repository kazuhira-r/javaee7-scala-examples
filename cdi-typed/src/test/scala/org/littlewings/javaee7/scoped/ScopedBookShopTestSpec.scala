package org.littlewings.javaee7.scoped

import javax.enterprise.inject.AmbiguousResolutionException
import javax.enterprise.inject.spi.CDI
import javax.enterprise.util.TypeLiteral

import org.littlewings.javaee7.WeldSpecSupport
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ScopedBookShopTestSpec extends FunSpec with WeldSpecSupport {
  describe("With Scoped, Non Typed Spec") {
    it("select BookShop type") {
      withWeld {
        val bookShop = CDI.current.select(classOf[BookShop]).get
        bookShop should not be (null)
        bookShop should be(a[BookShop])
        bookShop should be(a[Business])
        bookShop should be(a[Shop[_]])
      }
    }

    it("select Business type") {
      withWeld {
        val thrown = the[AmbiguousResolutionException] thrownBy CDI.current.select(classOf[Business]).get
        thrown.getMessage should include("Cannot resolve an ambiguous dependency between")
        thrown.getMessage should include("Managed Bean [class org.littlewings.javaee7.scoped.Business] with qualifiers [@Any @Default]")
        thrown.getMessage should include("Managed Bean [class org.littlewings.javaee7.scoped.BookShop] with qualifiers [@Any @Default]")
      }
    }

    it("select Shop type") {
      withWeld {
        val shop = CDI.current.select(new TypeLiteral[Shop[Book]] {}).get
        shop should not be (null)
        shop should be(a[BookShop])
        shop should be(a[Business])
        shop should be(a[Shop[_]])
      }
    }
  }
}
