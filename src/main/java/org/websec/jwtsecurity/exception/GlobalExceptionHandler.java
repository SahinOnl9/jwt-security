package org.websec.jwtsecurity.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.websec.jwtsecurity.model.ValidationError;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(InvalidBookException.class)
	public Set<String> handleInvalidBookException(InvalidBookException ex) {
		return ex.getErrSet();
	}

	/**
	 * Constraint violation exception handler
	 * 
	 * @param ex ConstraintViolationException
	 * @return List<ValidationError> - list of ValidationError built from set of
	 *         ConstraintViolation
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ConstraintViolationException.class)
	public List<ValidationError> handleConstraintViolation(ConstraintViolationException ex) {
		log.debug("Constraint violation exception encountered: {}", ex.getConstraintViolations(), ex);
		return buildValidationErrors(ex.getConstraintViolations());
	}

	/**
	 * Build list of ValidationError from set of ConstraintViolation
	 *
	 * @param violations Set<ConstraintViolation<?>> - Set of parameterized
	 *                   ConstraintViolations
	 * @return List<ValidationError> - list of validation errors
	 */
	private List<ValidationError> buildValidationErrors(Set<ConstraintViolation<?>> violations) {
		return violations.stream()
				.map(violation -> ValidationError.builder()
						.field(StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
								.reduce((first, second) -> second).orElse(null).toString())
						.error(violation.getMessage()).build())
				.collect(Collectors.toList());
	}
}