package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.E_PlanLoader;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * This is the configuration class, 'config.xml' serialized one.
 * 
 * @author bitmarte
 */
@XStreamAlias("config")
public class Config {

	/**
	 * The SeleniumRC URL, used to connect a remote hub, grid
	 */
	@XStreamAlias("seleniumRcURL")
	private String seleniumRcURL;

	/**
	 * @see BrowserConfig
	 */
	@XStreamAlias("browser")
	private BrowserConfig browser;

	/**
	 * The path where webdriver is installed
	 */
	@XStreamAlias("localWebDriverPath")
	private String localWebDriverPath;

	/**
	 * The timeout for element extractors, in seconds
	 */
	@XStreamAlias("maxTimeOutPerElementExtratorInSec")
	private long maxTimeOutPerElementExtratorInSec;

	/**
	 * The path of test reports
	 */
	@XStreamAlias("reportBaseDir")
	private String reportBaseDir;

	/**
	 * The list of customPlanLoaders for load test suite, {@link E_PlanLoader}
	 */
	@XStreamAlias("customPlanLoaders")
	private List<String> customPlanLoaders;

	/**
	 * The list of default {@link ErrorCondition}, applied to all run in case of
	 * no SuccessCondition and ErrorCondition run hits
	 */
	@XStreamAlias("errorConditions")
	private List<ErrorCondition> errorConditions;

	/**
	 * To close browser at the end of the test
	 */
	@XStreamAlias("closeBrowserOnFinish")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean closeBrowserOnFinish;

	/**
	 * To clean report directory instead of a new run
	 */
	@XStreamAlias("cleanReportBaseDirOnStart")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean cleanReportBaseDirOnStart;

	/**
	 * @see WebTimingsConfig
	 */
	@XStreamAlias("webTimings")
	private WebTimingsConfig webTimings;

	/**
	 * @see MobProxyConfig
	 */
	@XStreamAlias("mobProxy")
	private MobProxyConfig mobProxy;

	/**
	 * To activate concurrent plans run
	 */
	@XStreamAlias("concurrentPlans")
	@XStreamConverter(value = BooleanConverter.class, booleans = { true }, strings = { "true", "false" })
	private boolean concurrentPlans;

	/**
	 * @see BrowserActionExecutorConfig
	 */
	@XStreamAlias("browserActionExecutor")
	private BrowserActionExecutorConfig browserActionExecutor;

	/**
	 * Waiting timeout before take a screenshot, on a SuccessCondition
	 */
	@XStreamAlias("waitBeforeScreenshotInMilliSec")
	private long waitBeforeScreenshotInMilliSec;

	public String getSeleniumRcURL() {
		return seleniumRcURL;
	}

	public void setSeleniumRcURL(String seleniumRcURL) {
		this.seleniumRcURL = seleniumRcURL;
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

	public long getMaxTimeOutPerElementExtratorInSec() {
		return maxTimeOutPerElementExtratorInSec;
	}

	public void setMaxTimeOutPerElementExtratorInSec(long maxTimeOutPerElementExtratorInSec) {
		this.maxTimeOutPerElementExtratorInSec = maxTimeOutPerElementExtratorInSec;
	}

	public long getWaitBeforeScreenshotInMilliSec() {
		return waitBeforeScreenshotInMilliSec;
	}

	public void setWaitBeforeScreenshotInMilliSec(long waitBeforeScreenshotInMilliSec) {
		this.waitBeforeScreenshotInMilliSec = waitBeforeScreenshotInMilliSec;
	}

}
