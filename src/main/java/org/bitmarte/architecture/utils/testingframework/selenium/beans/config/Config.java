package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.E_PlanLoader;

import java.util.List;

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
     * The polling during element extractor for fluent waiting
     */
    @XStreamAlias("pollingPerElementExtractorInMillisec")
    private long pollingPerElementExtractorInMillisec;

    /**
     * The configuration for test reports
     */
    @XStreamAlias("reportConfig")
    private ReportConfig reportConfig;

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
     * To close browser at the end of the test, default <code>true</code>
     */
    @XStreamAlias("closeBrowserOnFinish")
    private String closeBrowserOnFinish;

    /**
     * To clean report directory instead of a new run
     */
    @XStreamAlias("cleanReportBaseDirOnStart")
    private String cleanReportBaseDirOnStart;

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
    private String concurrentPlans;

    /**
     * @see BrowserActionExecutorConfig
     */
    @XStreamAlias("browserActionExecutor")
    private BrowserActionExecutorConfig browserActionExecutor;

    /**
     * If <code>true</code> only in view page will be saved, if
     * <code>false</code> the whole page will be saved. Default value is
     * <code>false</code>
     */
    @XStreamAlias("inViewScreenshot")
    private String inViewScreenshot;

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

    public long getMaxTimeOutPerElementExtratorInSec() {
        return maxTimeOutPerElementExtratorInSec;
    }

    public void setMaxTimeOutPerElementExtratorInSec(long maxTimeOutPerElementExtratorInSec) {
        this.maxTimeOutPerElementExtratorInSec = maxTimeOutPerElementExtratorInSec;
    }

    public long getPollingPerElementExtractorInMillisec() {
        return pollingPerElementExtractorInMillisec;
    }

    public void setPollingPerElementExtractorInMillisec(long pollingPerElementExtractorInMillisec) {
        this.pollingPerElementExtractorInMillisec = pollingPerElementExtractorInMillisec;
    }

    public ReportConfig getReportConfig() {
        return reportConfig;
    }

    public void setReportConfig(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
    }

    public List<String> getCustomPlanLoaders() {
        return customPlanLoaders;
    }

    public void setCustomPlanLoaders(List<String> customPlanLoaders) {
        this.customPlanLoaders = customPlanLoaders;
    }

    public List<ErrorCondition> getErrorConditions() {
        return errorConditions;
    }

    public void setErrorConditions(List<ErrorCondition> errorConditions) {
        this.errorConditions = errorConditions;
    }

    public String isCloseBrowserOnFinish() {
        return closeBrowserOnFinish;
    }

    public void setCloseBrowserOnFinish(String closeBrowserOnFinish) {
        this.closeBrowserOnFinish = closeBrowserOnFinish;
    }

    public String isCleanReportBaseDirOnStart() {
        return cleanReportBaseDirOnStart;
    }

    public void setCleanReportBaseDirOnStart(String cleanReportBaseDirOnStart) {
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

    public String isConcurrentPlans() {
        return concurrentPlans;
    }

    public void setConcurrentPlans(String concurrentPlans) {
        this.concurrentPlans = concurrentPlans;
    }

    public BrowserActionExecutorConfig getBrowserActionExecutor() {
        return browserActionExecutor;
    }

    public void setBrowserActionExecutor(BrowserActionExecutorConfig browserActionExecutor) {
        this.browserActionExecutor = browserActionExecutor;
    }

    public String isInViewScreenshot() {
        return inViewScreenshot;
    }

    public void setInViewScreenshot(String inViewScreenshot) {
        this.inViewScreenshot = inViewScreenshot;
    }

    public long getWaitBeforeScreenshotInMilliSec() {
        return waitBeforeScreenshotInMilliSec;
    }

    public void setWaitBeforeScreenshotInMilliSec(long waitBeforeScreenshotInMilliSec) {
        this.waitBeforeScreenshotInMilliSec = waitBeforeScreenshotInMilliSec;
    }

}
