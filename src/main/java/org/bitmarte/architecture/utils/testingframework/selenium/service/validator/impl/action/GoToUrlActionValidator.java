package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class GoToUrlActionValidator extends A_Validator {

	public GoToUrlActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	public void validate() throws Exception {
		GoToUrlAction toValidate = (GoToUrlAction) this.inValidation;
		if (toValidate.getUrl() == null) {
			throw new ValidatorException("Node '<url>' is required!");
		}
	}

	public void setDefaultValue() throws Exception {
		GoToUrlAction toValidate = (GoToUrlAction) this.inValidation;

	}

}
