package org.littlewings.javaee7.service

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.littlewings.javaee7.extension.MyExtension

@ApplicationScoped
class ExtensionService {
  @Inject
  private var myExtension: MyExtension = null

  def extensionClases: Set[Class[_]] = Set.empty ++ myExtension.classes
}
