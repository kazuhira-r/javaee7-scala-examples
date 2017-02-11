package org.littlewings.javaee7.rest

import javax.servlet._
import javax.servlet.http.HttpServletResponse

class CorsFilter extends Filter {
  override def init(filterConfig: FilterConfig): Unit = ()

  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    val res = response.asInstanceOf[HttpServletResponse]
    res.addHeader("Access-Control-Allow-Origin", "*")
    res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    res.addHeader("Access-Control-Allow-Headers", "Content-Type")
    chain.doFilter(request, response)
  }

  override def destroy(): Unit = ()
}
