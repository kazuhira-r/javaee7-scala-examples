package org.littlewings.javaee7.interceptor

import javax.enterprise.context.Dependent
import javax.interceptor.{InvocationContext, AroundInvoke, Interceptor}

@Interceptor
@AddStar
@Dependent
@SerialVersionUID(1L)
class AddStarInterceptor extends Serializable {
  @AroundInvoke
  @throws(classOf[Exception])
  def invoke(ic: InvocationContext): Any =
    ic.proceed() match {
      case s: String => "★" + s + "★"
      case other => other
    }
}
