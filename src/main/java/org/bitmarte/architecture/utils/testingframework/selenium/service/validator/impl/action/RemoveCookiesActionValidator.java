package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * Validate the remove all cookies action configuration
 * 
 * @author bitmarte
 */
public class RemoveCookiesActionValidator extends A_Validator {

	public RemoveCookiesActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#validate()
	 */
	public void validate() throws Exception {
		RemoveCookiesAction toValidate = (RemoveCookiesAction) this.inValidation;
		if (toValidate.getCookiesName() == null) {
			throw new ValidatorException("Please set the cookiesName list!");
		} else {
			if (toValidate.getCookiesName().isEmpty()) {
				throw new ValidatorException("Please set one cookieName at least!");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#setDefaultValue()
	 */
	public void setDefaultValue() throws Exception {
		RemoveCookiesAction toValidate = (RemoveCookiesAction) this.inValidation;

	}

}
