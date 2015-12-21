package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class DriverUtils {

	private static final Logger LOG = LoggerFactory
			.getLogger(DriverUtils.class);

	/**
	 * It removes all browser cookies
	 * 
	 * @param driver
	 */
	public static void removeAllCookies(WebDriver driver) {
		LOG.info("Removing all cookies...");
		driver.manage().deleteAllCookies();
	}

	/**
	 * It removes passed cookies, comma separated values
	 * 
	 * @param driver
	 * @param cookiesCommaSeparatedValues
	 */
	public static void removeCookies(WebDriver driver,
			String cookiesCommaSeparatedValues) {
		StringTokenizer stringTokenizer = new StringTokenizer(
				cookiesCommaSeparatedValues, ",");
		while (stringTokenizer.hasMoreTokens()) {
			String cookieName = stringTokenizer.nextToken();
			LOG.info("Removing cookie '" + cookieName + "'...");
			driver.manage().deleteCookieNamed(cookieName);
		}
	}

	/**
	 * It resizes window's browser
	 * 
	 * @param driver
	 * @param wPx
	 * @param hPx
	 */
	public static void resizeWindow(WebDriver driver, int wPx, int hPx) {
		LOG.info("Setting custom window size: " + wPx + "px x " + hPx + "px");
		driver.manage().window().setPosition(new Point(0, 0));
		Dimension d = new Dimension(wPx, hPx);
		driver.manage().window().setSize(d);
	}

	/**
	 * Take a screenshot and save it with a specified name
	 * 
	 * @param driver
	 * @param fileName
	 * @param testResult
	 */
	@SuppressWarnings("finally")
	public static E_TestResult takeScreenshot(WebDriver driver,
			String fileName, E_TestResult testResult) {
		boolean hasError = false;
		try {
			LOG.debug("Take screenshot '"
					+ DefaultSeleniumConfig.getConfig().getScreenshotBaseDir()
					+ fileName + "_" + testResult.toString() + ".png'");

			WebDriver augmentedDriver = driver;
			if (DefaultSeleniumConfig.getConfig().getSeleniumRcURL() != null) {
				augmentedDriver = new Augmenter().augment(driver);
			}
			File scrFile = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(DefaultSeleniumConfig
					.getConfig().getScreenshotBaseDir()
					+ fileName
					+ "_"
					+ testResult.toString() + ".png"));
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

	public static void fullScreen(WebDriver driver) {
		LOG.info("Setup full screen window size...");
		driver.manage().window().maximize();
	}
}
