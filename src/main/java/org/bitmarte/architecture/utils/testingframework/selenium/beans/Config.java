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

	@XStreamAlias("browserMode")
	private String browserMode;

	@XStreamAlias("browserName")
	private String browserName;

	@XStreamAlias("localWebDriverPath")
	private String localWebDriverPath;

	@XStreamAlias("maxTimeOutPerSuccessConditionInSec")
	private int maxTimeOutPerSuccessConditionInSec;

	@XStreamAlias("maxTimeOutPerErrorConditionInSec")
	private int maxTimeOutPerErrorConditionInSec;

	@XStreamAlias("reportBaseDir")
	private String reportBaseDir;

	@XStreamAlias("errorConditions")
	private List<ErrorCondition> errorConditions;

	@XStreamAlias("closeBrowserOnFinish")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean closeBrowserOnFinish;

	@XStreamAlias("cleanReportBaseDirOnStart")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean cleanReportBaseDirOnStart;

	@XStreamAlias("webTimings")
	private WebTimingsConfig webTimings;
	
	@XStreamAlias("mobProxy")
	private MobProxyConfig mobProxy;

	public String getSeleniumRcURL() {
		return seleniumRcURL;
	}

	public void setSeleniumRcURL(String seleniumRcURL) {
		this.seleniumRcURL = seleniumRcURL;
	}

	public int getMaxTimeOutPerSuccessConditionInSec() {
		return maxTimeOutPerSuccessConditionInSec;
	}

	public void setMaxTimeOutPerSuccessConditionInSec(int maxTimeOutPerSuccessConditionInSec) {
		this.maxTimeOutPerSuccessConditionInSec = maxTimeOutPerSuccessConditionInSec;
	}

	public int getMaxTimeOutPerErrorConditionInSec() {
		return maxTimeOutPerErrorConditionInSec;
	}

	public void setMaxTimeOutPerErrorConditionInSec(int maxTimeOutPerErrorConditionInSec) {
		this.maxTimeOutPerErrorConditionInSec = maxTimeOutPerErrorConditionInSec;
	}

	public String getReportBaseDir() {
		return reportBaseDir;
	}

	public void setReportBaseDir(String reportBaseDir) {
		this.reportBaseDir = reportBaseDir;
	}

	public String getBrowserMode() {
		return browserMode;
	}

	public void setBrowserMode(String browserMode) {
		this.browserMode = browserMode;
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

	public boolean isCleanReportBaseDirOnStart() {
		return cleanReportBaseDirOnStart;
	}

	public void setCleanReportBaseDirOnStart(boolean cleanReportBaseDirOnStart) {
		this.cleanReportBaseDirOnStart = cleanReportBaseDirOnStart;
	}

	public WebTimingsConfig getWebTimings() {
		return webTimings;
	}

	public void setWebTimings(WebTimingsConfig webTimings) {
		this.webTimings = webTimings;
	}

	public MobProxyConfig getMobProxy() {
		return mobProxy;
	}

	public void setMobProxy(MobProxyConfig mobProxy) {
		this.mobProxy = mobProxy;
	}

}
