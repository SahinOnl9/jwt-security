package org.websec.jwtsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

	private String msg;
	private String desc;
	private int errCode;
	private String timestamp;

}
