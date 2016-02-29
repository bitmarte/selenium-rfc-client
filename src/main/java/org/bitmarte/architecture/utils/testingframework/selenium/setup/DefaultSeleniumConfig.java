package org.bitmarte.architecture.utils.testingframework.selenium.setup;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class DefaultSeleniumConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultSeleniumConfig.class);

	private static Config configuration = null;

	public static void loadConfiguration(String[] params) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Config.class);
			xStream.processAnnotations(ErrorCondition.class);
			File file = new File(params[0] + "/config.xml");
			configuration = (Config) xStream.fromXML(file);

			ValidatorHandler.execute(configuration);
		} catch (Exception e) {
			throw e;
		}
	}

	public static Config getConfig() {
		return configuration;
	}

}
