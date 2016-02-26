package org.bitmarte.architecture.utils.testingframework.selenium.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_Validator implements I_Validator {

	protected Object inValidation;

	protected static final Logger LOG = LoggerFactory.getLogger(A_Validator.class);

	public A_Validator(Object inValidation) throws Exception {
		this.inValidation = inValidation;

		LOG.debug("Validating...");
		this.validate();

		LOG.debug("Setting default value...");
		this.setDefaultValue();
	}

}
