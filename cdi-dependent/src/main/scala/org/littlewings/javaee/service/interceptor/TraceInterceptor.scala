package org.littlewings.javaee.service.interceptor

import javax.enterprise.context.Dependent
import javax.interceptor.{Interceptor, InvocationContext, AroundInvoke}

@Interceptor
@Trace
@Dependent
@SerialVersionUID(1L)
class TraceInterceptor extends Serializable {
  @AroundInvoke
  @throws(classOf[Exception])
  def invoke(ic: InvocationContext): Any = {
    println(s"[${ic.getTarget.getClass.getSuperclass.getSimpleName}] method[${ic.getMethod.getName}] start.")

    try {
      ic.proceed()
    } finally {
      println(s"[${ic.getTarget.getClass.getSuperclass.getSimpleName}] method[${ic.getMethod.getName}] end.")
    }
  }
}
