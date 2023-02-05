package org.websec.jwtsecurity.service;

import java.util.List;

import org.websec.jwtsecurity.model.AppUser;
import org.websec.jwtsecurity.model.Role;

public interface UserService {

	AppUser saveUser(AppUser appUser);

	Role saveRole(Role role);

	void addRoleToUser(String username, String roleName);

	AppUser getAppUser(String username);

	List<AppUser> getUsers();
}
