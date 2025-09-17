package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.entities.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandlers  {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlers.class);

	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<ErrorResponse> handleOutOfStock(OutOfStockException ex, WebRequest request) {
	    ErrorResponse error = new ErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            "Out of Stock",
	            ex.getMessage(),
	            request.getDescription(false)
	    );

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	   

	     @ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex){
		 logger.warn("Validation Failed");
		 
		 Map<String, String> errors = new HashMap<>();
		 ex.getBindingResult().getFieldErrors().forEach(error ->{
			 errors.put(error.getField(), error.getDefaultMessage());
		 });
		 
		 return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
		}
	    @ExceptionHandler(Exception.class)
	   public  ResponseEntity<ErrorResponse> allExceptionHander(Exception ex,WebRequest request){
		   logger.error("Unexpected error: ",ex.getMessage());
		   
		   ErrorResponse error = new ErrorResponse(
				        LocalDateTime.now(), 
				        HttpStatus.INTERNAL_SERVER_ERROR.value(),
				         "Internal server Error",
				         ex.getMessage(),
				         request.getDescription(false));
		   return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	

}
