package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;

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

	@XStreamAlias("browser")
	private BrowserConfig browser;

	@XStreamAlias("localWebDriverPath")
	private String localWebDriverPath;

	@XStreamAlias("maxTimeOutPerSuccessConditionInSec")
	private int maxTimeOutPerSuccessConditionInSec;

	@XStreamAlias("maxTimeOutPerErrorConditionInSec")
	private int maxTimeOutPerErrorConditionInSec;

	@XStreamAlias("maxTimeOutPerElementExtratorInSec")
	private int maxTimeOutPerElementExtratorInSec;

	@XStreamAlias("reportBaseDir")
	private String reportBaseDir;

	@XStreamAlias("customPlanLoaders")
	private List<String> customPlanLoaders;

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

	@XStreamAlias("concurrentPlans")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean concurrentPlans;

	@XStreamAlias("browserActionExecutor")
	private BrowserActionExecutorConfig browserActionExecutor;

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

	public List<String> getCustomPlanLoaders() {
		return customPlanLoaders;
	}

	public void setCustomPlanLoaders(List<String> customPlanLoaders) {
		this.customPlanLoaders = customPlanLoaders;
	}

	public BrowserConfig getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserConfig browser) {
		this.browser = browser;
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

	public boolean isConcurrentPlans() {
		return concurrentPlans;
	}

	public void setConcurrentPlans(boolean concurrentPlans) {
		this.concurrentPlans = concurrentPlans;
	}

	public BrowserActionExecutorConfig getBrowserActionExecutor() {
		return browserActionExecutor;
	}

	public void setBrowserActionExecutor(BrowserActionExecutorConfig browserActionExecutor) {
		this.browserActionExecutor = browserActionExecutor;
	}

	public int getMaxTimeOutPerElementExtratorInSec() {
		return maxTimeOutPerElementExtratorInSec;
	}

	public void setMaxTimeOutPerElementExtratorInSec(int maxTimeOutPerElementExtratorInSec) {
		this.maxTimeOutPerElementExtratorInSec = maxTimeOutPerElementExtratorInSec;
	}

}
