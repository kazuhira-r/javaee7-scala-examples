package org.littlewings.javaee7.cdi.trace

import javax.enterprise.context.Dependent
import javax.interceptor.{InvocationContext, AroundInvoke, Interceptor}

@Interceptor
@Trace
@Dependent
@SerialVersionUID(1L)
class TraceInterceptor extends Serializable {
  @AroundInvoke
  @throws(classOf[Exception])
  def invoke(ic: InvocationContext): Any = {
    println("start Interceptor.")

    try {
      ic.proceed()
    } finally {
      println("end Interceptor.")
    }
  }
}
