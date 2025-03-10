package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserActionExecutorConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserCapabilityConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.MobProxyConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.WebTimingsConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.A_Unmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 * @author bitmarte
 *
 */
public class ConfigUnmarshaller extends A_Unmarshaller {

	public Config unmarshall(File xmlFileInput) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.addPermission(AnyTypePermission.ANY);

			xStream.processAnnotations(Config.class);
			xStream.processAnnotations(ErrorCondition.class);
			xStream.processAnnotations(BrowserConfig.class);
			xStream.processAnnotations(BrowserActionExecutorConfig.class);
			xStream.processAnnotations(BrowserCapabilityConfig.class);
			xStream.processAnnotations(MobProxyConfig.class);
			xStream.processAnnotations(WebTimingsConfig.class);

			Config config = (Config) xStream.fromXML(xmlFileInput);

			ValidatorHandler.execute(config);

			return config;
		} catch (Exception e) {
			throw e;
		}
	}

}
