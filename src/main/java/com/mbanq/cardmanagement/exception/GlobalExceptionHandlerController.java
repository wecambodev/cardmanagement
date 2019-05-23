//package com.mbanq.cardmanagement.exception;
//
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandlerController {
//
//  @Bean
//  public ErrorAttributes errorAttributes() {
//    // Hide exception field in the return object
//    return new DefaultErrorAttributes() {
//
//
//    };
//  }
//
//  @ExceptionHandler(CustomException.class)
//  public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
//    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
//  }
//
////  @ExceptionHandler(AccessDeniedException.class)
////  public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
////    res.sendError(HttpStatus.FORBIDDEN.value(), "Resource : Access denied");
////  }
//
//  @ExceptionHandler(Exception.class)
//  public void handleException(HttpServletResponse res) throws IOException {
//    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
//  }
//
//}
