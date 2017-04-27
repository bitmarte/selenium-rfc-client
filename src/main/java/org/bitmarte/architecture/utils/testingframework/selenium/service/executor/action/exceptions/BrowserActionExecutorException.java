package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.exceptions;

/**
 * This is the custom browser action executor exception
 * 
 * @author bitmarte
 */
@SuppressWarnings("serial")
public class BrowserActionExecutorException extends Exception {

	public BrowserActionExecutorException(String errorMessage) {
		super(errorMessage);
	}
}
