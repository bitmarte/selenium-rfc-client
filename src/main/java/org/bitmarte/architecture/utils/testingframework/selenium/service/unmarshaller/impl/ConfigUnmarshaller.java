package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl;

import java.io.File;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.A_Unmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.I_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author A110282
 *
 */
public class ConfigUnmarshaller extends A_Unmarshaller {

	public Config unmarshall(File xmlFileInput) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Config.class);
			xStream.processAnnotations(ErrorCondition.class);

			Config config = (Config) xStream.fromXML(xmlFileInput);

			I_Validator v = ValidatorFactory.getInstance(config);
			v.validate();
			v.setDefaultValue();

			return config;
		} catch (Exception e) {
			throw e;
		}
	}

}
