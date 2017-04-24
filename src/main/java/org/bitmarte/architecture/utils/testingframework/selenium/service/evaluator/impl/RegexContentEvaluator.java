package org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.A_ContentEvaluator;

/**
 * This evaluator checks if the content respects a regular expression
 * 
 * @author bitmarte
 */
public class RegexContentEvaluator extends A_ContentEvaluator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.dom.evaluator.
	 * I_ContentEvaluator#evaluate(java .lang.String, java.lang.String)
	 */
	public boolean evaluate(String str1, String str2) {
		LOG.debug("using RegExContentEvaluator...");
		boolean result = false;
		Pattern p = Pattern.compile(str1);
		Matcher m = p.matcher(str2);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

}
