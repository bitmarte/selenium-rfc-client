package org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator;

import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.exceptions.ContentEvaluatorException;

/**
 * This is the content evaluator interface
 * 
 * @author bitmarte
 */
public interface I_ContentEvaluator {

	public boolean evaluate(String str1, String str2) throws ContentEvaluatorException;
}
