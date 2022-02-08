package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Registration;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface RegistrationService {

	public Registration addRegistration(Registration registration) throws AlreadyExistsException;

	public List<Registration> getAllRegistrations();

	public Optional<Registration> getRegistrationById(String string);

	public Registration updateRegistrationByEmail(String email, Registration registration) throws IdNotFoundException, AlreadyExistsException;

	public String deleteRegistrationById(String string) throws IdNotFoundException;

}
