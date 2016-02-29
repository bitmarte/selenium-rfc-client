package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.exceptions;

/**
 * @author bitmarte
 *
 */
@SuppressWarnings("serial")
public class UnmarshallException extends Exception {

	public UnmarshallException(String errorMessage) {
		super(errorMessage);
	}

	public UnmarshallException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}
