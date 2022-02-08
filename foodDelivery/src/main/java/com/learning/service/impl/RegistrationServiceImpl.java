package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Login;
import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.LoginRepo;
import com.learning.repo.RegistrationRepo;
import com.learning.service.LoginService;
import com.learning.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	LoginRepo loginRepo;

	@Autowired
	LoginService loginService;

	@Override
	public Registration addRegistration(Registration registration) throws AlreadyExistsException {
		Optional<Registration> optional = registrationRepo.findById(registration.getRegistrationId());
		if (optional.isPresent()) {
			throw new AlreadyExistsException(
					"Record With " + registration.getRegistrationId() + " id Present in Registration Table!");
		}
		Registration registration2 = registrationRepo.save(registration);
		if (registration2 != null) {
			Login login = new Login(registration.getEmail(), registration.getPassword(), registration);
			if (loginRepo.existsByEmail(registration.getEmail())) {
				throw new AlreadyExistsException(
						"Record With " + registration.getEmail() + " email Present in Login Table!");
			}
			String result = loginService.addLogin(login);
			if (result.equals("success")) {
				return registration;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public List<Registration> getAllRegistrations() {
		return registrationRepo.findAll();
	}

	@Override
	public Optional<Registration> getRegistrationById(String string) {
		return registrationRepo.findById(string);
	}

	@Override
	public Registration updateRegistrationByEmail(String email, Registration registration) throws IdNotFoundException{
		if(!registrationRepo.existsByEmail(email)) {
			throw new IdNotFoundException("No user with " + registration.getRegistrationId() + " found.");
		}
		return registrationRepo.save(registration);
		}

	@Override
	public String deleteRegistrationById(String string) throws IdNotFoundException {
		Optional<Registration> optional = registrationRepo.findById(string);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("No record with id " + string + " found");
		} else {
			registrationRepo.deleteById(string);
			return "success";
		}
	}
}
