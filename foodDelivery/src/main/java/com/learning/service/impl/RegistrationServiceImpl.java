package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Login;
import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
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
		if (optional.isEmpty()) {
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
	public String getAllRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRegistrationById(Integer integer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRegistrationByEmail(String email, Registration registration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRegistrationById(Integer integer) {
		// TODO Auto-generated method stub
		return null;
	}

}
