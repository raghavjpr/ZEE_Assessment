package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.RegistrationService;

@RestController
@RequestMapping
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws AlreadyExistsException {
		Registration registration2 = registrationService.addRegistration(registration);
		return ResponseEntity.status(201).body(registration2);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> validateRegistration() {
		return null;

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUserDetails() {
		List<Registration> list = registrationService.getAllRegistrations();
		if (list.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Message", "No Records in Table!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		Registration[] array = new Registration[list.size()];
		return ResponseEntity.status(200).body(list.toArray(array));

	}

	@GetMapping("/users/{registrationId}")
	public ResponseEntity<?> getUserById(@PathVariable("registrationId") String registrationId) {
		Optional<Registration> optional = registrationService.getRegistrationById(registrationId);
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Message", "Sorry user with " + registrationId + " not found.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}

	@PutMapping("/users/userId")
	public ResponseEntity<?> updateUser(Registration registration) {
		return null;

	}

	@DeleteMapping("/users/{registrationId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("registrationId") String registrationId)
			throws IdNotFoundException {
		String string = registrationService.deleteRegistrationById(registrationId);
		if (string.equals("fail")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Message", "Sorry user with " + registrationId + " not found.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("Message", "User Deleted Successfully");
		return ResponseEntity.status(200).body(map);

	}

}
