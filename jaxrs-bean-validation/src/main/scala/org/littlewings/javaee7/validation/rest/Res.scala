package org.littlewings.javaee7.validation.rest

import scala.beans.BeanProperty

object Res {
  def apply(name: String, num: Int, subs: java.util.List[Sub]): Res = {
    val res = new Res
    res.name = name
    res.num = num
    res.subs = subs
    res
  }
}

class Res {
  @BeanProperty
  var name: String = _
  @BeanProperty
  var num: Int = _
  @BeanProperty
  var subs: java.util.List[Sub] = _
}
