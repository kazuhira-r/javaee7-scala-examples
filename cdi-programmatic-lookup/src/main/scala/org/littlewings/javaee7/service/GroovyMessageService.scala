package org.littlewings.javaee7.service

import javax.enterprise.context.RequestScoped

import org.littlewings.javaee7.qualifier.HelloGroovy

@HelloGroovy
@RequestScoped
class GroovyMessageService extends MessageService {
  override def get: String = "Hello Groovy"
}
