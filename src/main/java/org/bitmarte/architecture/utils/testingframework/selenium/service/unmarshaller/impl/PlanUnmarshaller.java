package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Authentication;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.InputField;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.A_Unmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.I_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author A110282
 *
 */
public class PlanUnmarshaller extends A_Unmarshaller {

	public Plan unmarshall(File xmlFileInput) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Plan.class);
			xStream.processAnnotations(Authentication.class);
			xStream.processAnnotations(Run.class);
			xStream.processAnnotations(BrowserAction.class);
			xStream.processAnnotations(InputField.class);
			xStream.processAnnotations(SuccessCondition.class);
			xStream.processAnnotations(ErrorCondition.class);

			Plan plan = (Plan) xStream.fromXML(xmlFileInput);

			I_Validator v = ValidatorFactory.getInstance(plan);
			v.validate();
			v.setDefaultValue();

			String planFileName = StringUtils.substring(xmlFileInput.getName(), 0,
					xmlFileInput.getName().lastIndexOf("."));
			plan.setPlanName(planFileName);

			return plan;
		} catch (Exception e) {
			throw e;
		}
	}

}
