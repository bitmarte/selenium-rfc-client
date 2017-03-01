package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("successCondition")
public class SuccessCondition extends A_TestCondition {

	@XStreamAlias("waitBeforeScreenshotInMilliSec")
	@XStreamAsAttribute
	private long waitBeforeScreenshotInMilliSec;

	@XStreamAlias("screenshotFileName")
	@XStreamAsAttribute
	private String screenshotFileName;

	public long getWaitBeforeScreenshotInMilliSec() {
		return waitBeforeScreenshotInMilliSec;
	}

	public void setWaitBeforeScreenshotInMilliSec(long waitBeforeScreenshotInMilliSec) {
		this.waitBeforeScreenshotInMilliSec = waitBeforeScreenshotInMilliSec;
	}

	public String getScreenshotFileName() {
		return screenshotFileName;
	}

	public void setScreenshotFileName(String screenshotFileName) {
		this.screenshotFileName = screenshotFileName;
	}

}
