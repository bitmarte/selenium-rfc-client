package org.bitmarte.architecture.utils.testingframework.selenium.setup;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_WebDriver;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class DefaultSeleniumConfig {

	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultSeleniumConfig.class);

	private static Config configuration = null;

	public static void loadConfiguration(String[] params) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Config.class);
			xStream.processAnnotations(ErrorCondition.class);
			File file = new File(params[0] + "/config.xml");
			configuration = (Config) xStream.fromXML(file);

			configValidation();
		} catch (Exception e) {
			throw e;
		}
	}

	public static Config getConfig() {
		return configuration;
	}

	private static void configValidation() throws Exception {
		if (getConfig().getBrowserName() == null) {
			throw new ConfigException("Property 'browserName' is required!");
		} else {
			E_WebDriver e_WebDriver = null;
			try {
				e_WebDriver = E_WebDriver.valueOf(getConfig().getBrowserName());
			} catch (Exception e) {
				throw new ConfigException("We are sorry but '"
						+ getConfig().getBrowserName() + "' is not supported!");
			}

			switch (e_WebDriver) {
			case FIREFOX_REMOTE:
				if (getConfig().getSeleniumRcURL() == null) {
					throw new ConfigException(
							"Property 'seleniumRcURL' is required for 'FIREFOX_REMOTE' configuration!");
				}
				break;
			case CHROME_REMOTE:
				if (getConfig().getSeleniumRcURL() == null) {
					throw new ConfigException(
							"Property 'seleniumRcURL' is required for 'CHROME_REMOTE' configuration!");
				}
				break;
			case CHROME_LOCAL:
				if (getConfig().getLocalWebDriverPath() == null) {
					throw new ConfigException(
							"Property 'localWebDriverPath' is required for 'CHROME_LOCAL' configuration!");
				}
				break;
			default:
				throw new ConfigException("Unknown case on e_WebDriver enum!");
			}
		}

		if (getConfig().getMaxTimeOutPerPageInSec() == -1) {
			throw new ConfigException(
					"Property 'maxTimeOutPerPageInSec' is required!");
		}
		if (getConfig().getScreenshotBaseDir() == null) {
			throw new ConfigException(
					"Property 'screenshotBaseDir' is required!");
		}
	}

}
