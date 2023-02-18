package org.websec.jwtsecurity.exception;

import java.util.Set;

public class InvalidBookException extends RuntimeException {
	private static final long serialVersionUID = 8828022369229131949L;

	private Set<String> errSet;

	public InvalidBookException(Set<String> errSet) {
		this.errSet = errSet;
	}

	public Set<String> getErrSet() {
		return errSet;
	}

	@Override
	public String getMessage() {
		return this.errSet.toString();
	}

}
