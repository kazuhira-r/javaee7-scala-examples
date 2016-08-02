package org.littlewings.javaee7.cdi

import javax.annotation.Priority
import javax.interceptor.{AroundInvoke, Interceptor, InvocationContext}

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@EnableTracing
@SerialVersionUID(1L)
class TraceInterceptor extends Serializable {

  @AroundInvoke
  def trace(ic: InvocationContext): Any = {
    val targetClass: Class[_] = ic.getTarget.getClass.getSuperclass
    val method = ic.getMethod

    println(s"[start] ${targetClass.getSimpleName}#${method.getName}")

    val result = ic.proceed()

    println(s"[ end ] ${targetClass.getSimpleName}#${method.getName}")

    result
  }
}
