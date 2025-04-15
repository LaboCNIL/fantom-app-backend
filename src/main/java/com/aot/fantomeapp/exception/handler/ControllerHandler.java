package com.aot.fantomeapp.exception.handler;

import com.aot.fantomeapp.exception.ApiError;
import com.aot.fantomeapp.exception.FunctionalException;
import com.aot.fantomeapp.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
@Slf4j
public class ControllerHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });

      log.error(ex.getMessage(), ex);
      ApiError apiError = new ApiError(LocalDateTime.now(), errors.toString(), ex.toString(), HttpStatus.BAD_REQUEST,
         HttpStatus.BAD_REQUEST.value());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
   }

   @ExceptionHandler(FunctionalException.class)
   public final ResponseEntity<ApiError> handleFunctionalError(FunctionalException ex) {
      log.error(ex.getMessage(), ex);
      ApiError apiError = new ApiError(LocalDateTime.now(), ex.getClientMessage(), ex.toString(), ex.getHttpStatus(),
         ex.getHttpStatus().value());
      return ResponseEntity.status(ex.getHttpStatus()).body(apiError);
   }

   @ExceptionHandler(TechnicalException.class)
   public final ResponseEntity<ApiError> handleTechnicalError(TechnicalException ex) {
      log.error(ex.getMessage(), ex);
      ApiError apiError = new ApiError(LocalDateTime.now(), ex.getClientMessage(), ex.toString(), ex.getHttpStatus(),
         ex.getHttpStatus().value());
      return ResponseEntity.status(ex.getHttpStatus()).body(apiError);
   }

   @ExceptionHandler(Throwable.class)
   public final ResponseEntity<ApiError> handleThrowableError(Throwable ex) {
      log.error(ex.getMessage(), ex);
      ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ex.toString(),
         HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
   }

   // Handler for PreAuthorize
   @ExceptionHandler(AccessDeniedException.class)
   public final ResponseEntity<ApiError> handleAuthError(Exception ex) {
      log.error(ex.getMessage(), ex);
      ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ex.toString(), HttpStatus.UNAUTHORIZED,
         HttpStatus.UNAUTHORIZED.value());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
   }

}
