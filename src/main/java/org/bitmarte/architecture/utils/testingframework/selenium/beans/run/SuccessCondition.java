package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This is the concrete success condition
 * 
 * @author bitmarte
 */
@XStreamAlias("successCondition")
public class SuccessCondition extends A_TestCondition {

	/**
	 * The file name of the screenshot
	 */
	@XStreamAlias("screenshotFileName")
	@XStreamAsAttribute
	private String screenshotFileName;

	public String getScreenshotFileName() {
		return screenshotFileName;
	}

	public void setScreenshotFileName(String screenshotFileName) {
		this.screenshotFileName = screenshotFileName;
	}

}
