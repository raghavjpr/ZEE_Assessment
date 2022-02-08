package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Registration;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Integer> {

	boolean existsByEmail(String email);

}
