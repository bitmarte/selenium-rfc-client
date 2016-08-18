package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class InputFillActionValidator extends A_Validator {

	public InputFillActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	public void validate() throws Exception {
		InputFillAction toValidate = (InputFillAction) this.inValidation;
		if (toValidate.getElement() == null) {
			throw new ValidatorException("Node '<element> is required!'");
		}
		if (toValidate.getValue() == null) {
			throw new ValidatorException("Node '<value> is required!'");
		}
	}

	public void setDefaultValue() throws Exception {
		InputFillAction toValidate = (InputFillAction) this.inValidation;

	}

}
