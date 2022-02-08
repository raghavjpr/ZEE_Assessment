package com.learning.service;

import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;

public interface RegistrationService {

	public Registration addRegistration(Registration registration) throws AlreadyExistsException;

	public String getAllRegistrations();

	public String getRegistrationById(Integer integer);

	public String updateRegistrationByEmail(String email, Registration registration);

	public String deleteRegistrationById(Integer integer);

}
