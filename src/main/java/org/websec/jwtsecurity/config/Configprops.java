package org.websec.jwtsecurity.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource(value = "classpath:configprops.json", factory = JsonPropertySourceFactory.class)
@Data
@ConfigurationProperties
public class Configprops {

	private List<String> authDisabledPaths;
}
