package org.bitmarte.architecture.utils.testingframework.selenium.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the generic validator, abstract one
 * 
 * @author bitmarte
 */
public abstract class A_Validator implements I_Validator {

	protected Object inValidation;

	protected static final Logger LOG = LoggerFactory.getLogger(A_Validator.class);

	public A_Validator(Object inValidation) throws Exception {
		this.inValidation = inValidation;
	}

}
