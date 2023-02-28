package org.websec.jwtsecurity.controller;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.websec.jwtsecurity.model.AppUser;
import org.websec.jwtsecurity.model.Role;
import org.websec.jwtsecurity.service.UserService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private UserService userService;

	@PostMapping
	public void getToken(@RequestParam String username, @RequestParam String password) {}

	@GetMapping("/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		String authKey = "Bearer ";

		if (authorizationHeader != null && authorizationHeader.startsWith(authKey)) {
			try {
				String refreshToken = authorizationHeader.substring(authKey.length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

				String username = decodedJWT.getSubject();
				AppUser appUser = userService.getAppUser(username);

				String accessToken = JWT.create().withSubject(appUser.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);

				Map<String, String> tokenMap = new HashMap<>(2);
				tokenMap.put("access_token", accessToken);
				tokenMap.put("refresh_token", refreshToken);

				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokenMap);
			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());

				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("error_message", e.getMessage());
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), errorMap);

			}
		} else {
			throw new InvalidParameterException("Error: refresh token is missing");
		}

	}
	
	@GetMapping("/x")
	public void error() {
		throw new NullPointerException("Dummy Exception");
	}
}
