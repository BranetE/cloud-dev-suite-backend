package com.branet.cloud.dev.suite.shiftservice.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** The type Global exception handler. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle conflict error response.
   *
   * @param req the req
   * @param e the e
   * @return the error response
   */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(EntityExistsException.class)
  @ResponseBody
  ErrorResponse handleConflict(HttpServletRequest req, Exception e) {
    return new ErrorResponse(e.getMessage(), req.getRequestURL().toString());
  }

  /**
   * Default handler error response.
   *
   * @param req the req
   * @param e the e
   * @return the error response
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @SneakyThrows
  @ResponseBody
  ErrorResponse defaultHandler(HttpServletRequest req, Exception e) {
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) throw e;

    return new ErrorResponse(e.getMessage(), req.getRequestURL().toString());
  }
}
