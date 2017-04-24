package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.IFrameSwitchAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * Validate the iframe switch action configuration
 * 
 * @author bitmarte
 */
public class IFrameSwitchActionValidator extends A_Validator {

	public IFrameSwitchActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#validate()
	 */
	public void validate() throws Exception {
		IFrameSwitchAction toValidate = (IFrameSwitchAction) this.inValidation;
		if (toValidate.getElement() == null) {
			throw new ValidatorException("Node '<element> is required!'");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#setDefaultValue()
	 */
	public void setDefaultValue() throws Exception {
		IFrameSwitchAction toValidate = (IFrameSwitchAction) this.inValidation;

	}

}
