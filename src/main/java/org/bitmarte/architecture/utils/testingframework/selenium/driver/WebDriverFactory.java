package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.net.URL;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_WebDriver;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class WebDriverFactory {

	private static final Logger LOG = LoggerFactory
			.getLogger(WebDriverFactory.class);

	public static WebDriver getInstance(String browserName) throws Exception {
		E_WebDriver e_WebDriver = E_WebDriver.FIREFOX_LOCAL;
		try {
			e_WebDriver = E_WebDriver.valueOf(browserName);
		} catch (Exception e) {
			// TODO: handle exception
		}

		DesiredCapabilities capabilities = null;

		switch (e_WebDriver) {
		case FIREFOX_LOCAL:
			LOG.info("using local firefox browser");
			return new FirefoxDriver();
		case FIREFOX_REMOTE:
			LOG.info("using remote firefox browser on server '"
					+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
					+ "'");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			return new RemoteWebDriver(new URL(DefaultSeleniumConfig
					.getConfig().getSeleniumRcURL()), capabilities);
		case CHROME_LOCAL:
			LOG.info("using local chrome browser");
			System.setProperty("webdriver.chrome.driver", DefaultSeleniumConfig
					.getConfig().getLocalWebDriverPath());
			return new ChromeDriver();
		case CHROME_REMOTE:
			LOG.info("using remote chrome browser on server '"
					+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
					+ "'");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			return new RemoteWebDriver(new URL(DefaultSeleniumConfig
					.getConfig().getSeleniumRcURL()), capabilities);
		case IEXPLORER_REMOTE:
			LOG.info("using remote iexplorer browser on server '"
					+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
					+ "'");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			return new RemoteWebDriver(new URL(DefaultSeleniumConfig
					.getConfig().getSeleniumRcURL()), capabilities);

		default:
			LOG.warn("using default WebDriver implementation: local firefox browser");
			return new FirefoxDriver();
		}
	}
}
