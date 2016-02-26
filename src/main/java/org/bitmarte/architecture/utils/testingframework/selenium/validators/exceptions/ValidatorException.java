package org.bitmarte.architecture.utils.testingframework.selenium.validators.exceptions;

/**
 * @author bitmarte
 *
 */
@SuppressWarnings("serial")
public class ValidatorException extends Exception {

	public ValidatorException(String errorMessage) {
		super(errorMessage);
	}

	public ValidatorException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}
