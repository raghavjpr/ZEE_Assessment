package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping("/addRegistration")
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws AlreadyExistsException {
		Registration registration2 = registrationService.addRegistration(registration);
		return ResponseEntity.status(201).body(registration2);
		
	}

}
