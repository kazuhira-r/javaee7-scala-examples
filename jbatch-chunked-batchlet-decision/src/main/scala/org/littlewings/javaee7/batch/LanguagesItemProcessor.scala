package org.littlewings.javaee7.batch

import javax.batch.api.chunk.ItemProcessor
import javax.enterprise.context.Dependent
import javax.inject.Named

@Dependent
@Named
class LanguagesItemProcessor extends ItemProcessor {
  override def processItem(item: AnyRef): AnyRef = item
}
