package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("config")
public class Config {

	@XStreamAlias("seleniumRcURL")
	private String seleniumRcURL;

	@XStreamAlias("browserName")
	private String browserName;

	@XStreamAlias("localWebDriverPath")
	private String localWebDriverPath;

	@XStreamAlias("maxTimeOutPerPageInSec")
	private int maxTimeOutPerPageInSec = -1;

	@XStreamAlias("screenshotBaseDir")
	private String screenshotBaseDir;

	@XStreamAlias("errorConditions")
	private List<ErrorCondition> errorConditions;

	@XStreamAlias("closeBrowserOnFinish")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = {
			"true", "false" })
	private boolean closeBrowserOnFinish;

	public String getSeleniumRcURL() {
		return seleniumRcURL;
	}

	public void setSeleniumRcURL(String seleniumRcURL) {
		this.seleniumRcURL = seleniumRcURL;
	}

	public int getMaxTimeOutPerPageInSec() {
		return maxTimeOutPerPageInSec;
	}

	public void setMaxTimeOutPerPageInSec(int maxTimeOutPerPageInSec) {
		this.maxTimeOutPerPageInSec = maxTimeOutPerPageInSec;
	}

	public String getScreenshotBaseDir() {
		return screenshotBaseDir;
	}

	public void setScreenshotBaseDir(String screenshotBaseDir) {
		this.screenshotBaseDir = screenshotBaseDir;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getLocalWebDriverPath() {
		return localWebDriverPath;
	}

	public void setLocalWebDriverPath(String localWebDriverPath) {
		this.localWebDriverPath = localWebDriverPath;
	}

	public List<ErrorCondition> getErrorConditions() {
		return errorConditions;
	}

	public void setErrorConditions(List<ErrorCondition> errorConditions) {
		this.errorConditions = errorConditions;
	}

	public boolean isCloseBrowserOnFinish() {
		return closeBrowserOnFinish;
	}

	public void setCloseBrowserOnFinish(boolean closeBrowserOnFinish) {
		this.closeBrowserOnFinish = closeBrowserOnFinish;
	}
}
