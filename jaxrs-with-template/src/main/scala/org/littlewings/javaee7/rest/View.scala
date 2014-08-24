package org.littlewings.javaee7.rest

trait View {
  val templatePath: String
  val binding: Map[String, _]
}

case class VelocityView(val templatePath: String, val binding: Map[String, _]) extends View

case class HandlebarsView(val templatePath: String, val binding: Map[String, _]) extends View
