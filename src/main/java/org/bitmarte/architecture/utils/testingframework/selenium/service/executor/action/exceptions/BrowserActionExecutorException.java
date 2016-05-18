package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.exceptions;

/**
 * @author bitmarte
 *
 */
@SuppressWarnings("serial")
public class BrowserActionExecutorException extends Exception {

	public BrowserActionExecutorException(String errorMessage) {
		super(errorMessage);
	}
}
