package org.littlewings.javaee7.cdi

import javax.enterprise.context.Dependent
import javax.interceptor.{AroundInvoke, InvocationContext, Interceptor}

@Interceptor
@AddBrackets
@Dependent
@SerialVersionUID(1L)
class AddBracketsInterceptor extends Serializable {
  @AroundInvoke
  @throws(classOf[Exception])
  def invoke(ic: InvocationContext): Any =
    ic.proceed() match {
      case s: String => s"[$s]"
      case other => other
    }
}
