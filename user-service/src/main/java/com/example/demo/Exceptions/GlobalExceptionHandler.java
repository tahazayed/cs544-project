package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public class ErrorResponse {
    private String errorMessage;
    
        // Getter and setter (or use lombok annotations)
        public void setErrorMessage(String str) {
            this.errorMessage = str;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        
        // You can customize the error response based on the exception or add more information
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}