package org.bitmarte.architecture.utils.testingframework.selenium.setup;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.UnmarshallerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class DefaultSeleniumConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultSeleniumConfig.class);

	private static Config configuration = null;

	public static void loadConfiguration(String[] params) throws Exception {
		try {
			configuration = (Config) UnmarshallerFactory.getInstance(Config.class)
					.unmarshall(new File(params[0] + "/config.xml"));
		} catch (Exception e) {
			throw e;
		}
	}

	public static Config getConfig() {
		return configuration;
	}

}
