package org.websec.jwtsecurity.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ConfigPathHelper {
	private static List<String> authDisabledPaths;

	public static List<String> getAuthDisabledPaths() {
		return authDisabledPaths == null ? readAuthDisabledPaths() : authDisabledPaths;
	}

	private static List<String> readAuthDisabledPaths() {
		try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configprops.json")) {
			authDisabledPaths = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readValue(inputStream, JsonNode.class);
			jsonNode.get("authDisabledPaths").elements().forEachRemaining(path -> authDisabledPaths.add(path.asText()));
			return authDisabledPaths;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
