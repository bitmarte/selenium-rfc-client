package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
			String archivePath = SeleniumConfigProvider.getConfig().getReportBaseDir() + this.planName + "/screenshots/";

			LOG.debug("Take screenshot '" + archivePath + screenshotFileName + ".png'");

			if (run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec() != 0) {
				LOG.info("waiting before take screenshot ["
						+ run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec() + "ms] ...");
				Thread.sleep(run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec());
			}

			WebDriver augmentedDriver = this.driver;
			if (SeleniumConfigProvider.getConfig().getSeleniumRcURL() != null) {
				augmentedDriver = new Augmenter().augment(this.driver);
			}
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(archivePath + screenshotFileName + ".png"));
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
