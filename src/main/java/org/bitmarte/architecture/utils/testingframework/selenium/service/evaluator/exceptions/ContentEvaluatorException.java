package org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.exceptions;

/**
 * Custom exception of content evaluator executor
 * 
 * @author bitmarte
 */
@SuppressWarnings("serial")
public class ContentEvaluatorException extends Exception {

	public ContentEvaluatorException(String errorMessage) {
		super(errorMessage);
	}
}
