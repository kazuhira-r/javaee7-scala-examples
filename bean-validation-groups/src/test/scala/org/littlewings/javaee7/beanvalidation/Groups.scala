package org.littlewings.javaee7.beanvalidation

import javax.validation.GroupSequence
import javax.validation.groups.Default

trait A

trait B

trait ExtendsDefault extends Default

@GroupSequence(Array(classOf[Default], classOf[A]))
trait DefaultWithA

@GroupSequence(Array(classOf[A], classOf[B]))
trait AwithB
