package org.websec.jwtsecurity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.websec.jwtsecurity.model.AppUser;
import org.websec.jwtsecurity.model.Role;
import org.websec.jwtsecurity.repository.AppUserRepo;
import org.websec.jwtsecurity.repository.RoleRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private AppUserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findByUsername(username);
		if (user == null) {
			log.error("User not found!");
			throw new UsernameNotFoundException("User not found!");
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public AppUser saveUser(AppUser appUser) {
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		return userRepo.save(appUser);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public AppUser getAppUser(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<AppUser> getUsers() {
		return userRepo.findAll();
	}

}
