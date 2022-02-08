package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Login;
import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.LoginService;
import com.learning.service.RegistrationService;

@RestController
@RequestMapping
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	LoginService loginService;

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Registration registration) throws AlreadyExistsException {

		Registration result = registrationService.addRegistration(registration);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Login login) {
		String result = loginService.authenticate(login);
		if (result.equals("success")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Success");
			return ResponseEntity.status(200).body(map);
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Fail");
			return ResponseEntity.status(403).body(map);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		Registration[] registrations = registrationService.getAllRegistrations();
		if (registrations.length == 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.status(200).body(registrations);
	}

	@GetMapping("/users/{registrationId}")
	public ResponseEntity<?> getUserById(@PathVariable("registrationId") int registrationId)
			throws IdNotFoundException {
		Registration registration = registrationService.getRegistrationById(registrationId);
		return ResponseEntity.status(200).body(registration);
	}

	@PutMapping("/update/users")
	public ResponseEntity<?> updateUserById(@RequestBody Registration registration)
			throws IdNotFoundException, AlreadyExistsException {
		Registration result = registrationService.updateRegistration(registration);
		return ResponseEntity.status(200).body(result);
	}

	@DeleteMapping("/delete/{registrationId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("registrationId") int registrationId)
			throws IdNotFoundException {
		String result = registrationService.deleteRegistrationById(registrationId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", result);
		return ResponseEntity.status(201).body(map);

	}

}
