package org.littlewings.javaee7.beanvalidation

import javax.validation.Valid
import javax.validation.constraints.{Max, Pattern}
import javax.validation.groups.{ConvertGroup, Default}

import scala.beans.BeanProperty

class User {
  @BeanProperty
  @Valid
  var name: Name = _

  @BeanProperty
  @Max.List(
    Array(
      new Max(value = 12),
      new Max(value = 32, groups = Array(classOf[GroupA]))
    )
  )
  var age: Int = _
}

class Name {
  @BeanProperty
  @Pattern.List(
    Array(
      new Pattern(regexp = "^(カツオ|ワカメ)$"),
      new Pattern(regexp = "^(サザエ|マスオ|タラオ)$", groups = Array(classOf[GroupA]))
    )
  )
  var first: String = _

  @BeanProperty
  @Pattern.List(
    Array(
      new Pattern(regexp = "^磯野$"),
      new Pattern(regexp = "^フグ田$", groups = Array(classOf[GroupA]))
    )
  )
  var last: String = _
}

class User2 {
  @BeanProperty
  @Valid
  @ConvertGroup.List(
    Array(
      new ConvertGroup(from = classOf[Default], to = classOf[GroupB]),
      new ConvertGroup(from = classOf[GroupA], to = classOf[GroupC])
    )
  )
  var name: Name2 = _

  @BeanProperty
  @Max.List(
    Array(
      new Max(value = 12),
      new Max(value = 32, groups = Array(classOf[GroupA]))
    )
  )
  var age: Int = _
}

class Name2 {
  @BeanProperty
  @Pattern.List(
    Array(
      new Pattern(regexp = "^(カツオ|ワカメ)$", groups = Array(classOf[GroupB])),
      new Pattern(regexp = "^(サザエ|マスオ|タラオ)$", groups = Array(classOf[GroupC]))
    )
  )
  var first: String = _

  @BeanProperty
  @Pattern.List(
    Array(
      new Pattern(regexp = "^磯野$", groups = Array(classOf[GroupB])),
      new Pattern(regexp = "^フグ田$", groups = Array(classOf[GroupC]))
    )
  )
  var last: String = _
}
