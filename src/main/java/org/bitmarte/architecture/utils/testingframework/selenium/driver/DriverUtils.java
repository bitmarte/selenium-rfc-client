package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.util.StringTokenizer;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author A110282
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
		LOG.debug("Removing all cookies...");
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
			LOG.debug("Removing cookie '" + cookieName + "'...");
			driver.manage().deleteCookieNamed(cookieName);
		}
	}
}
