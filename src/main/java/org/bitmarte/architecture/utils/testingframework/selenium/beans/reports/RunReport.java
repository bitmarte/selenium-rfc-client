package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

/**
 * @author bitmarte
 *
 */
public class RunReport extends A_Report {

	private WebTimings webTimings;

	private String harFilePath;

	public WebTimings getWebTimings() {
		return webTimings;
	}

	public void setWebTimings(WebTimings webTimings) {
		this.webTimings = webTimings;
	}

	public String getHarFilePath() {
		return harFilePath;
	}

	public void setHarFilePath(String harFilePath) {
		this.harFilePath = harFilePath;
	}

}
