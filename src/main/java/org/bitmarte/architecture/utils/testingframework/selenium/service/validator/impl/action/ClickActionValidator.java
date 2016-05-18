package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;

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

	}

	public void setDefaultValue() throws Exception {
		ClickAction toValidate = (ClickAction) this.inValidation;

	}

}
