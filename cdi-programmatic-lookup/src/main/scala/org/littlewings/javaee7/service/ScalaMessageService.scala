package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

import org.littlewings.javaee7.qualifier.HelloScala

@HelloScala
@RequestScoped
class ScalaMessageService extends MessageService {
  override def get: String = "Hello Scala"
}
