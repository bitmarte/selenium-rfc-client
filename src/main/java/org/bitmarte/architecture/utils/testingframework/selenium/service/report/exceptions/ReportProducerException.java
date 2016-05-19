package org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions;

/**
 * @author bitmarte
 *
 */
@SuppressWarnings("serial")
public class ReportProducerException extends Exception {

	public ReportProducerException(String errorMessage) {
		super(errorMessage);
	}

	public ReportProducerException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}
