package org.websec.jwtsecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTHelper {

	private DecodedJWT decodedJWT;

	public JWTHelper(String authToken) {
		String authKey = "Bearer ";
		String token = authToken.substring(authKey.length());
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		JWTVerifier jwtVerifier = JWT.require(algorithm).build();
		this.decodedJWT = jwtVerifier.verify(token);
	}

	public String getUsername() {
		return decodedJWT.getSubject();
	}

}
