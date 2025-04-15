package com.aot.fantomeapp.exception;

import com.aot.fantomeapp.exception.rules.TechnicalRule;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TechnicalException extends RuntimeException {

   private final HttpStatus httpStatus;
   private final String clientMessage;

   public TechnicalException(TechnicalRule rule, HttpStatus httpStatus) {
      super(rule.toString());
      this.httpStatus = httpStatus;
      this.clientMessage = rule.getMessage();
   }

   public TechnicalException(TechnicalRule rule, HttpStatus httpStatus, Throwable e) {
      super(rule.toString(), e);
      this.httpStatus = httpStatus;
      this.clientMessage = rule.getMessage();
   }

   public TechnicalException(TechnicalRule rule) {
      super(rule.toString());
      this.clientMessage = rule.getMessage();
      this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
   }

   public TechnicalException(TechnicalRule rule, Throwable e) {
      super(rule.toString(), e);
      this.clientMessage = rule.getMessage();
      this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
   }
}
