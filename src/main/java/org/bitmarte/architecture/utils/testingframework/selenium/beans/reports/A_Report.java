package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;

/**
 * @author bitmarte
 *
 */
public abstract class A_Report {

	private E_TestResult testResult;

	public E_TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(E_TestResult testResult) {
		this.testResult = testResult;
	}

}
