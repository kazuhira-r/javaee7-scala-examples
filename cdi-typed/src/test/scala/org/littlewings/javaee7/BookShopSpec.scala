package org.littlewings.javaee7

import javax.enterprise.inject.spi.CDI
import javax.enterprise.util.TypeLiteral

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class BookShopSpec extends FunSpec with WeldSpecSupport {
  describe("Non Scoped Non Typed Spec") {
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
        val business = CDI.current.select(classOf[Business]).get
        business should be(a[BookShop])
        business should be(a[Business])
        business should be(a[Shop[_]])
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
