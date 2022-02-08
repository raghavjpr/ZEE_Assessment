package com.learning.controlleradvice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.exception.AlreadyExistsException;

public class ExceptionAdvice {

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(AlreadyExistsException e) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", "Record Already Exists: " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}

}
