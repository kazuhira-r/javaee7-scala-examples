package org.littlewings.javaee7.typed

import javax.enterprise.inject.UnsatisfiedResolutionException
import javax.enterprise.inject.spi.CDI
import javax.enterprise.util.TypeLiteral

import org.littlewings.javaee7.WeldSpecSupport
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class TypedBookShopSpec extends FunSpec with WeldSpecSupport {
  describe("Non Typed Spec") {
    it("select BookShop type") {
      withWeld {
        val thrown = the[UnsatisfiedResolutionException] thrownBy CDI.current.select(classOf[BookShop]).get
        thrown.getMessage should include("Unable to resolve any beans for Type: class org.littlewings.javaee7.typed.BookShop; Qualifiers: []")
      }
    }

    it("select Business type") {
      withWeld {
        val thrown = the[UnsatisfiedResolutionException] thrownBy CDI.current.select(classOf[Business]).get
        thrown.getMessage should include("Unable to resolve any beans for Type: class org.littlewings.javaee7.typed.Business; Qualifiers: []")
      }
    }

    it("select Shop type") {
      withWeld {
        val shop = CDI.current.select(new TypeLiteral[Shop[Book]] {}).get
        shop should not be (null)
        shop should not be(a[BookShop])
        shop should not be(a[Business])
        shop should be(a[Shop[_]])
      }
    }
  }
}
