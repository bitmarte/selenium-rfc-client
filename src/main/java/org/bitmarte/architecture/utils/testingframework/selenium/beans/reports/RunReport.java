package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

/**
 * The run report concrete class
 * 
 * @author bitmarte
 */
public class RunReport extends A_Report {

	/**
	 * The W3C timings
	 */
	private WebTimingsReport webTimings;

	/**
	 * The HAR file path
	 */
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
