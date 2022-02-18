package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Role;
import com.learning.entity.enums.EROLE;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(EROLE roleName);

}