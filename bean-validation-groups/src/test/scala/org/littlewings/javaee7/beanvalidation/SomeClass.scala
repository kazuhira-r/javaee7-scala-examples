package org.littlewings.javaee7.beanvalidation

import javax.validation.groups.Default

class SomeClass {
  @Alphabetical
  @MySize(min = 3, max = 5, groups = Array(classOf[A]))
  var field1: String = _

  @Alphabetical(groups = Array(classOf[Default], classOf[A]))
  @MySize(min = 3, max = 5, groups = Array(classOf[A], classOf[B]))
  var field2: String = _

  @Alphabetical(groups = Array(classOf[Default], classOf[B]))
  @MySize(min = 3, max = 5)
  var field3: String = _

  @Alphabetical(groups = Array(classOf[ExtendsDefault]))
  @MySize(min = 3, max = 5, groups = Array(classOf[ExtendsDefault]))
  var field4: String = _
}
