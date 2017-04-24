package org.bitmarte.architecture.utils.testingframework.selenium.service.configuration;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.UnmarshallerFactory;

/**
 * This is the configuration provider, load configuration
 * 
 * @author bitmarte
 */
public class SeleniumConfigProvider {

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
