package com.learning.controlleradvice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.apierror.ApiError;
import com.learning.payload.response.MessageResponse;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyExistsExceptionHandler(AlreadyExistsException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleConstraintViolation() {
		return null;
	}

}
