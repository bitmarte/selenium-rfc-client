package org.bitmarte.architecture.utils.testingframework.selenium.utils;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import net.lightbody.bmp.BrowserMobProxy;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.E_ElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * This is the web driver utility class
 *
 * @author bitmarte
 */
public class DriverUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DriverUtils.class);

    private WebDriver driver;
    private String planName;

    public DriverUtils(WebDriver driver, String planName) {
        this.driver = driver;
        this.planName = planName;
    }

    /**
     * Take a screenshot
     *
     * @param run
     * @param testResult
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public E_TestResult takeScreenshot(Run run, E_TestResult testResult) throws Exception {
        if (testResult == null) {
            testResult = E_TestResult.valueOf(run.getRunReport().getTestResult());
        }

        String screenshotFileName = run.getRunName() + "_" + testResult.toString();
        if (run.getSuccessCondition().getScreenshotFileName() != null) {
            screenshotFileName = run.getSuccessCondition().getScreenshotFileName();
            LOG.info("using custom screenshot filename: " + screenshotFileName);
        }

        boolean hasError = false;
        try {
            String archivePath = SeleniumConfigProvider.getConfig().getReportConfig().getBaseDir() + this.planName
                    + "/screenshots/";

            LOG.debug("Take screenshot '" + archivePath + screenshotFileName + ".png'");

            long waitBeforeScreenshotInMilliSec = SeleniumConfigProvider.getConfig()
                    .getWaitBeforeScreenshotInMilliSec();
            switch (testResult) {
                case SUCCESS:
                    if (run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec() != 0) {
                        waitBeforeScreenshotInMilliSec = run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec();
                    }
                    break;
                default:
                    // Nothing to do
                    break;
            }

            if (Boolean.parseBoolean(SeleniumConfigProvider.getConfig().isInViewScreenshot())) {
                // in view screenshot
                if (waitBeforeScreenshotInMilliSec > 0) {
                    LOG.info("waiting before take screenshot [" + waitBeforeScreenshotInMilliSec + "ms] ...");
                    Thread.sleep(waitBeforeScreenshotInMilliSec);
                }
                Shutterbug.shootPage(this.driver, ScrollStrategy.VIEWPORT_ONLY).withName(screenshotFileName)
                        .save(archivePath);
            } else {
                // whole page screenshot
                if (waitBeforeScreenshotInMilliSec > 0) {
                    LOG.info("waiting before take screenshot [" + waitBeforeScreenshotInMilliSec + "ms] ...");
                    Thread.sleep(waitBeforeScreenshotInMilliSec);
                }
                Shutterbug.shootPage(this.driver, ScrollStrategy.WHOLE_PAGE).withName(screenshotFileName)
                        .save(archivePath);
            }

        } catch (Exception e) {
            hasError = true;
            LOG.error("Error takeScreenshot()!", e);
        } finally {
            if (hasError) {
                return E_TestResult.ERROR;
            } else {
                return testResult;
            }
        }
    }

    /**
     * Configure BrowserMobProxy
     *
     * @param proxy
     */
    public static void browserMobConfigure(BrowserMobProxy proxy) {
        // Trust all certificates
        proxy.setMitmDisabled(true);

        // ChainedProxy
        if (SeleniumConfigProvider.getConfig().getMobProxy().getChainedProxy() != null) {
            String host = null;
            int port = -1;
            StringTokenizer stringTokenizer = new StringTokenizer(
                    SeleniumConfigProvider.getConfig().getMobProxy().getChainedProxy(), ":");
            while (stringTokenizer.hasMoreTokens()) {
                host = stringTokenizer.nextToken();
                port = Integer.parseInt(stringTokenizer.nextToken());
            }
            proxy.setChainedProxy(new InetSocketAddress(host, port));
        }

        // port
        proxy.start(SeleniumConfigProvider.getConfig().getMobProxy().getPort());

        // DownloadBytePerSec
        if (SeleniumConfigProvider.getConfig().getMobProxy().getDownloadBytePerSec() > 0) {
            LOG.info("setting DownloadBytePerSec: "
                    + SeleniumConfigProvider.getConfig().getMobProxy().getDownloadBytePerSec() + " bps");
            proxy.setReadBandwidthLimit(SeleniumConfigProvider.getConfig().getMobProxy().getDownloadBytePerSec());
        }

        // UploadBytePerSec
        if (SeleniumConfigProvider.getConfig().getMobProxy().getUploadBytePerSec() > 0) {
            LOG.info("setting UploadBytePerSec: "
                    + SeleniumConfigProvider.getConfig().getMobProxy().getUploadBytePerSec() + " bps");
            proxy.setWriteBandwidthLimit(SeleniumConfigProvider.getConfig().getMobProxy().getUploadBytePerSec());
        }

        // Latency
        if (SeleniumConfigProvider.getConfig().getMobProxy().getLatencyInMillisec() > 0) {
            LOG.info("setting LatencyInMillisec: "
                    + SeleniumConfigProvider.getConfig().getMobProxy().getLatencyInMillisec() + " msec");
            proxy.setLatency(SeleniumConfigProvider.getConfig().getMobProxy().getLatencyInMillisec(),
                    TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Highlight element ONLY if BY_XPATH ElementExtractor used to
     *
     * @param currentRun
     * @param parentXpath
     * @throws Exception
     */
    public void elementHighlighting(Run currentRun, String parentXpath) throws Exception {
        if (E_ElementExtractor.BY_XPATH.name().equals(currentRun.getSuccessCondition().getElementExtractor())) {
            String element = currentRun.getSuccessCondition().getElement();
            if (parentXpath != null) {
                element = parentXpath;
            }
            try {
                WebElement el = ElementExtractorFactory
                        .getInstance(currentRun.getSuccessCondition().getElementExtractor()).getElementNoWait(this.driver,
                                element, currentRun.getSuccessCondition());
                ((JavascriptExecutor) this.driver).executeScript("arguments[0].style.border='3px solid red'", el);
                LOG.info("Element highlighted!");
            } catch (Exception e) {
                LOG.warn("Element not highlighted!");
                String newEl = StringUtils.substring(element, 0, StringUtils.lastIndexOf(element, "/"));
                LOG.debug("try elementHighlighting on: " + newEl);
                elementHighlighting(currentRun, newEl);
            }

        }
    }

}
