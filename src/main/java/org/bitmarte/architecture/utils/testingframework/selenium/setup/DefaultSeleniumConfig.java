package org.bitmarte.architecture.utils.testingframework.selenium.setup;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class DefaultSeleniumConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultSeleniumConfig.class);
	private static final int MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC = 10;
	private static final int MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC = 2;

	private static Config configuration = null;

	public static void loadConfiguration(String[] params) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Config.class);
			xStream.processAnnotations(ErrorCondition.class);
			File file = new File(params[0] + "/config.xml");
			configuration = (Config) xStream.fromXML(file);

			configValidation();
			setDefaultValues();
		} catch (Exception e) {
			throw e;
		}
	}

	public static Config getConfig() {
		return configuration;
	}

	private static void setDefaultValues() {
		LOG.debug("Setting defalut config...");
		if (getConfig().getMaxTimeOutPerSuccessConditionInSec() == 0) {
			getConfig().setMaxTimeOutPerSuccessConditionInSec(MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerSuccessConditionInSec = " + MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
		}
		if (getConfig().getMaxTimeOutPerErrorConditionInSec() == 0) {
			getConfig().setMaxTimeOutPerErrorConditionInSec(MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerErrorConditionInSec = " + MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
		}
		if (!getConfig().isCloseBrowserOnFinish()) {
			getConfig().setCloseBrowserOnFinish(true);
			LOG.info("setCloseBrowserOnFinish = true");
		}
	}

	private static void configValidation() throws Exception {
		// checking for required configuration
		if (getConfig().getBrowserName() == null) {
			throw new ConfigException("Property 'browserName' is missing!");
		}
		if (getConfig().getBrowserMode() == null) {
			throw new ConfigException("Property 'browserMode' is missing!");
		}
		if (getConfig().getReportBaseDir() == null) {
			throw new ConfigException("Property 'reportBaseDir' is missing!");
		} else {
			if (!(StringUtils.endsWith(getConfig().getReportBaseDir(), "/")
					|| StringUtils.endsWith(getConfig().getReportBaseDir(), "\\"))) {
				getConfig().setReportBaseDir(getConfig().getReportBaseDir().concat("/"));
				LOG.warn(
						"Property 'reportBaseDir' must have a slash '\\' or '/' at the end! - I fix it for you from now");
			}
		}

		// checking for allowed values
		try {
			E_BrowserName.valueOf(getConfig().getBrowserName());
		} catch (Exception e) {
			throw new ConfigException(
					"Value '" + getConfig().getBrowserName() + "' for property 'browserName' is not allowed!");
		}
		try {
			E_BrowserMode.valueOf(getConfig().getBrowserMode());
		} catch (Exception e) {
			throw new ConfigException(
					"Value '" + getConfig().getBrowserMode() + "' for property 'browserMode' is not allowed!");
		}

		// checking for restrictions
		if (E_BrowserMode.valueOf(getConfig().getBrowserMode()).equals(E_BrowserMode.LOCAL)
				&& E_BrowserName.valueOf(getConfig().getBrowserName()).equals(E_BrowserName.IEXPLORER)) {
			throw new ConfigException("BrowserName 'IEXPLORER' for browserMode 'LOCAL' is not allowed!");
		}
		if (E_BrowserMode.valueOf(getConfig().getBrowserMode()).equals(E_BrowserMode.REMOTE)
				&& getConfig().getSeleniumRcURL() == null) {
			throw new ConfigException("Property 'seleniumRcURL' for browserMode 'REMOTE' is required!");
		}
		if (E_BrowserMode.valueOf(getConfig().getBrowserMode()).equals(E_BrowserMode.LOCAL)
				&& !E_BrowserName.valueOf(getConfig().getBrowserName()).equals(E_BrowserName.FIREFOX)
				&& getConfig().getLocalWebDriverPath() == null) {
			throw new ConfigException("Property 'localWebDriverPath' for browserName '" + getConfig().getBrowserName()
					+ "' is required!");
		}

	}

}
