package org.websec.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websec.jwtsecurity.model.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
