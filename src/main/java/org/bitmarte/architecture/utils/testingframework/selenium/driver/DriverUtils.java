package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
	 * It removes all browser cookies
	 */
	public void removeAllCookies() {
		LOG.info("Removing all cookies...");
		this.driver.manage().deleteAllCookies();
	}

	/**
	 * It removes passed cookies, comma separated values
	 *
	 * @param cookiesCommaSeparatedValues
	 */
	public void removeCookies(String cookiesCommaSeparatedValues) {
		StringTokenizer stringTokenizer = new StringTokenizer(cookiesCommaSeparatedValues, ",");
		while (stringTokenizer.hasMoreTokens()) {
			String cookieName = stringTokenizer.nextToken();
			LOG.info("Removing cookie '" + cookieName + "'...");
			this.driver.manage().deleteCookieNamed(cookieName);
		}
	}

	/**
	 * It resizes window's browser
	 *
	 * @param wPx
	 * @param hPx
	 */
	public void resizeWindow(int wPx, int hPx) {
		LOG.info("Setting custom window size: " + wPx + "px x " + hPx + "px");
		this.driver.manage().window().setPosition(new Point(0, 0));
		Dimension d = new Dimension(wPx, hPx);
		this.driver.manage().window().setSize(d);
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
			String archivePath = DefaultSeleniumConfig.getConfig().getReportBaseDir() + this.planName + "/screenshots/";

			LOG.debug("Take screenshot '" + archivePath + screenshotFileName + ".png'");

			if (run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec() != 0) {
				LOG.info("waiting before take screenshot ["
						+ run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec() + "ms] ...");
				Thread.sleep(run.getSuccessCondition().getWaitBeforeScreenshotInMilliSec());
			}

			WebDriver augmentedDriver = this.driver;
			if (DefaultSeleniumConfig.getConfig().getSeleniumRcURL() != null) {
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

	/**
	 * Setup full screen window
	 */
	public void fullScreen() {
		LOG.info("Setup full screen window size...");
		this.driver.manage().window().maximize();
	}

	/**
	 * Manage browser action
	 *
	 * @param browserAction
	 */
	public void makeBrowserAction(BrowserAction browserAction) throws Exception {
		try {
			switch (E_BrowserAction.valueOf(browserAction.getAction())) {
			case REFRESH:
				LOG.info("window refreshing...");
				this.driver.navigate().refresh();
				break;
			case BACK:
				LOG.info("window back...");
				this.driver.navigate().back();
				break;
			case FORWARD:
				LOG.info("window forward...");
				this.driver.navigate().forward();
				break;
			case IFRAME_SWITCH:
				this.driver.switchTo()
						.frame(this.driver.findElements(By.xpath(browserAction.getElementByXPath())).get(0));
				break;

			default:
				LOG.info("using default action: window refreshing...");
				this.driver.navigate().refresh();
				break;
			}
		} catch (Exception e) {
			LOG.error("Error on makeBrowserAction '" + browserAction.getAction() + "' !", e);
			throw new WebDriverException("Error on browserAction '" + browserAction.getAction() + "'!");
		}

	}
}
