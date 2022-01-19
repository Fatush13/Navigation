package ee.task.nagivation.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
class ValidationExceptionHandler {

   @ResponseBody
   @ExceptionHandler
   protected ResponseEntity<String> problem(MethodArgumentNotValidException e) {
      List<FieldError> errors = e.getFieldErrors();

      String message = errors.get(errors.size() - 1).getDefaultMessage();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
   }
}

