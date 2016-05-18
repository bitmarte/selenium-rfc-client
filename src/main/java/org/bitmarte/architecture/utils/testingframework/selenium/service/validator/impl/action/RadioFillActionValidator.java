package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RadioFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class RadioFillActionValidator extends A_Validator {

	public RadioFillActionValidator(Object inValidation) throws Exception {
		super(inValidation);
		// TODO Auto-generated constructor stub
	}

	public void validate() throws Exception {
		RadioFillAction toValidate = (RadioFillAction) this.inValidation;
		if (toValidate.getElement() == null) {
			throw new ValidatorException("Node '<element> is required!'");
		}
		if (toValidate.getValue() == null) {
			throw new ValidatorException("Node '<value> is required!'");
		}
	}

	public void setDefaultValue() throws Exception {
		RadioFillAction toValidate = (RadioFillAction) this.inValidation;

	}

}
