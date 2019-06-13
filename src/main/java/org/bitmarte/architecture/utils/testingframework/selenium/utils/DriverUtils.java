package org.bitmarte.architecture.utils.testingframework.selenium.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

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
			String archivePath = SeleniumConfigProvider.getConfig().getReportBaseDir() + this.planName
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

}
