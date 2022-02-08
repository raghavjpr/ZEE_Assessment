package com.learning.service;

import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface RegistrationService {

	Registration addRegistration(Registration registration) throws AlreadyExistsException;

	String deleteRegistrationById(int registrationId) throws IdNotFoundException;

	Registration[] getAllRegistrations();

	Registration getRegistrationById(int registrationId) throws IdNotFoundException;

	Registration updateRegistration(Registration registration) throws IdNotFoundException, AlreadyExistsException;

}