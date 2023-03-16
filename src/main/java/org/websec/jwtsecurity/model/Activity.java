package org.websec.jwtsecurity.model;

public enum Activity {
	START("Start of activity"), 
	END("End of activity");

	private String text;

	private Activity(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
