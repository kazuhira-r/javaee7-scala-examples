package org.littlewings.javaee7.rest

import scala.beans.BeanProperty

case class Book(@BeanProperty isbn: String, @BeanProperty title: String, @BeanProperty price: Int)
