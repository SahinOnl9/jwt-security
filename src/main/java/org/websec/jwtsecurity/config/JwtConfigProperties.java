package org.websec.jwtsecurity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwtconfig")
public class JwtConfigProperties {

	private int accessTokenValidity;

	private int refreshTokenValidity;

	private String secretKey;
}
