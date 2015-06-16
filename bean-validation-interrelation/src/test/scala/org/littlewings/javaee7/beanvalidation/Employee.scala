package org.littlewings.javaee7.beanvalidation

import scala.beans.BeanProperty

@PostSalaryConstraint
class Employee {
  var name: String = _
  var post: String = _
  var salary: Int = _
}
