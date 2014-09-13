package org.littlewings.javaee7.validation.rest

import scala.annotation.tailrec
import scala.collection.JavaConverters._

import java.text.MessageFormat

import javax.validation.{ConstraintViolationException, Validation, ValidationException}
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.ext.{ExceptionMapper, Provider}

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation
import org.jboss.resteasy.api.validation.ResteasyViolationException

@Provider
class ValidationExceptionMapper extends ExceptionMapper[ValidationException] {
  override def toResponse(e: ValidationException): Response =
    e match {
      case rve: ResteasyViolationException =>
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(Map("messages" -> rve
                                      .getViolations
                                      .asScala
                                      .map { v => MessageFormat.format(v.getMessage, v.getPath) }
                                      .asJava).asJava)
          .`type`(MediaType.APPLICATION_JSON)  // またはResteasyViolationException#getAccept
          .build
      case cve: ConstraintViolationException =>
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(Map("messages" -> cve.getConstraintViolations).asJava)
          .`type`(MediaType.APPLICATION_JSON)
          .build
      case _ =>
        Response
          .serverError
          .entity(exceptionAsString(e))
          .header("validation-exception", "true")
          .`type`(MediaType.TEXT_PLAIN)
          .build
    }

  private def exceptionAsString(e: Exception): String =
    e match {
      case null => ""
      case _ =>
        (Vector(s"${e.getClass.getName}")
          ++ toStackTrace(e)
          ++ causeWhile(e, Vector.empty))
          .mkString(System.lineSeparator)
    }

  @tailrec
  private def causeWhile(th: Throwable, acc: Vector[String]): Vector[String] =
    th.getCause match {
      case null => acc
      case cause =>
        causeWhile(cause, ((acc :+ s"Cause by: ${cause.getMessage}") ++ toStackTrace(cause)))
    }

  private def toStackTrace(th: Throwable): Vector[String] =
    th.getStackTrace.foldLeft(Vector.empty[String]) {
      (acc, elm) => acc :+ s" at ${elm.toString}"
    }
}
