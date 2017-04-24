package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;

/**
 * This is the generic report, abstract one
 * 
 * @author bitmarte
 */
public abstract class A_Report {

	/**
	 * @see E_TestResult
	 */
	private String testResult;

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}
