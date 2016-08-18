package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class RemoveCookiesActionValidator extends A_Validator {

	public RemoveCookiesActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

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

	public void setDefaultValue() throws Exception {
		RemoveCookiesAction toValidate = (RemoveCookiesAction) this.inValidation;

	}

}
