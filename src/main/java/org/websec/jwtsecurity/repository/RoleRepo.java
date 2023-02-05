package org.websec.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websec.jwtsecurity.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
