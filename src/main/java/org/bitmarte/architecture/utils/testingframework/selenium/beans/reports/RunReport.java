package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

/**
 * @author bitmarte
 *
 */
public class RunReport extends A_Report {

	private WebTimingsReport webTimings;

	private String harFilePath;

	public WebTimingsReport getWebTimings() {
		return webTimings;
	}

	public void setWebTimings(WebTimingsReport webTimings) {
		this.webTimings = webTimings;
	}

	public String getHarFilePath() {
		return harFilePath;
	}

	public void setHarFilePath(String harFilePath) {
		this.harFilePath = harFilePath;
	}

}
