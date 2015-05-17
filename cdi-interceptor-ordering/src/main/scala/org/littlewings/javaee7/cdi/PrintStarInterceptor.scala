package org.littlewings.javaee7.cdi

import javax.annotation.Priority
import javax.enterprise.context.Dependent
import javax.interceptor.{InvocationContext, AroundInvoke, Interceptor}

@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 1)
@PrintStar
@Dependent
@SerialVersionUID(1L)
class PrintStarInterceptor extends Serializable {
  @AroundInvoke
  def invoke(ic: InvocationContext): Any = {
    println("★★★★★ start.")

    try {
      ic.proceed()
    } finally {
      println("★★★★★ end.")
    }
  }
}
