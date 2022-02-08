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

@Service // using this we get the singleton object
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private LoginRepo loginRepo;
	@Autowired
	private LoginService loginService;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Registration addRegistration(Registration registration) throws AlreadyExistsException {

		boolean status = registrationRepo.existsByEmail(registration.getEmail());
		if (status) {
			throw new AlreadyExistsException("EMAIL ALREADY EXISTS!");
		}

		Registration registration2 = registrationRepo.save(registration);
		if (registration2 != null) {
			Login login = new Login(registration.getEmail(), registration.getPassword(), registration2);
			if (loginRepo.existsByEmail(registration.getEmail())) {
				throw new AlreadyExistsException("USER ALREADY EXISTS IN LOGIN");
			}
			String result = loginService.addLogin(login);
			if (result == "success") {
				return registration2;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Registration updateRegistration(Registration registration)
			throws IdNotFoundException, AlreadyExistsException {

		if (!registrationRepo.existsById(registration.getRegistrationId())) {
			throw new IdNotFoundException("Sorry user with " + registration.getRegistrationId() + " not found");
		}
		return registrationRepo.save(registration);
	}

	@Override
	public Registration getRegistrationById(int registrationId) throws IdNotFoundException {
		Optional<Registration> optional = registrationRepo.findById(registrationId);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("sorry " + registrationId + " not found");
		} else {
			return optional.get();
		}
	}

	@Override
	public Registration[] getAllRegistrations() {
		List<Registration> list = registrationRepo.findAll();
		if (list.isEmpty()) {
			return null;
		}
		Registration[] array = new Registration[list.size()];
		return list.toArray(array);
	}

	@Override
	public String deleteRegistrationById(int registrationId) throws IdNotFoundException {

		if (!registrationRepo.existsById(registrationId)) {
			throw new IdNotFoundException("sorry user with id " + registrationId + " not found");
		}
		registrationRepo.deleteById(registrationId);
		return "User Successfully deleted";
	}
}