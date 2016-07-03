package org.littlewings.javaee7.jta

import javax.transaction.Synchronization

class MySynchronization extends Synchronization {
  private var calledBeforeCompletaion: Boolean = _
  private var afterCompletionStatus: Int = -1

  def getCalledBeforeCompletion: Boolean = calledBeforeCompletaion
  def getAfterCompletionStatus: Int = afterCompletionStatus

  override def afterCompletion(status: Int): Unit = {
    afterCompletionStatus = status
    println(s"afterCompletion, status = ${status}")
  }

  override def beforeCompletion(): Unit = {
    calledBeforeCompletaion = true
    println("beforeCompletion")
  }
}
