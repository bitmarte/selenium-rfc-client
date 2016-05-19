package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class ClickActionValidator extends A_Validator {

	public ClickActionValidator(Object inValidation) throws Exception {
		super(inValidation);
		// TODO Auto-generated constructor stub
	}

	public void validate() throws Exception {
		ClickAction toValidate = (ClickAction) this.inValidation;
		if (toValidate.getElement() == null) {
			throw new ValidatorException("Node '<element>' is required!");
		}
	}

	public void setDefaultValue() throws Exception {
		ClickAction toValidate = (ClickAction) this.inValidation;

	}

}
