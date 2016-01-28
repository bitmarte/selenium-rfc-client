package org.bitmarte.architecture.utils.testingframework.selenium.driver;

import java.net.URL;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
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

	public static WebDriver getInstance(String browserMode, String browserName)
			throws Exception {
		E_BrowserMode e_BrowserMode = E_BrowserMode.LOCAL;
		E_BrowserName e_BrowserName = E_BrowserName.FIREFOX;
		try {
			e_BrowserMode = E_BrowserMode.valueOf(browserMode);
			e_BrowserName = E_BrowserName.valueOf(browserName);
		} catch (Exception e) {
			throw new ConfigException(
					"Unsupported testing mode for browserMode '" + browserMode
							+ "' and browserName '" + browserName + "'");
		}

		DesiredCapabilities capabilities = null;

		switch (e_BrowserMode) {
		case REMOTE:
			switch (e_BrowserName) {
			case CHROME:
				LOG.info("using remote chrome browser on server '"
						+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
						+ "'");
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				return new RemoteWebDriver(new URL(DefaultSeleniumConfig
						.getConfig().getSeleniumRcURL()), capabilities);
			case IEXPLORER:
				LOG.info("using remote iexplorer browser on server '"
						+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
						+ "'");
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("internet explorer");
				return new RemoteWebDriver(new URL(DefaultSeleniumConfig
						.getConfig().getSeleniumRcURL()), capabilities);
			case FIREFOX:
				LOG.info("using remote firefox browser on server '"
						+ DefaultSeleniumConfig.getConfig().getSeleniumRcURL()
						+ "'");
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("firefox");
				return new RemoteWebDriver(new URL(DefaultSeleniumConfig
						.getConfig().getSeleniumRcURL()), capabilities);

			default:
				throw new ConfigException("Unknown case on E_BrowserName enum!");
			}
		case LOCAL:
			// Using local mode as default browserMode
			switch (e_BrowserName) {
			case CHROME:
				LOG.info("using local chrome browser");
				System.setProperty("webdriver.chrome.driver",
						DefaultSeleniumConfig.getConfig()
								.getLocalWebDriverPath());
				return new ChromeDriver();
			case IEXPLORER:
				throw new ConfigException(
						"IExplorer browser in remote mode is not supported!");
			default:
				// Using Firefox as default local browser
				LOG.info("using local firefox browser");
				return new FirefoxDriver();
			}

		default:
			throw new ConfigException("Unknown case on E_BrowserMode enum!");
		}
	}
}
