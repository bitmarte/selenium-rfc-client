package org.bitmarte.architecture.utils.testingframework.selenium.service.validator;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.ConfigValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.PlanValidator;

/**
 * @author bitmarte
 *
 */
public class ValidatorFactory {

	public static I_Validator getInstance(Object inValidation) throws Exception {
		if (inValidation instanceof Config) {
			return new ConfigValidator((Config) inValidation);
		}
		if (inValidation instanceof Plan) {
			return new PlanValidator((Plan) inValidation);
		}

		throw new ValidatorException("Unknown object type: " + inValidation.getClass().getName());
	}
}
