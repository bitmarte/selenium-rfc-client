package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ComboFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * Validate the combo fill action configuration
 * 
 * @author bitmarte
 */
public class ComboFillActionValidator extends A_Validator {

	public ComboFillActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#validate()
	 */
	public void validate() throws Exception {
		ComboFillAction toValidate = (ComboFillAction) this.inValidation;
		if (toValidate.getElement() == null) {
			throw new ValidatorException("Node '<element> is required!'");
		}
		if (toValidate.getValue() == null) {
			throw new ValidatorException("Node '<value> is required!'");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#setDefaultValue()
	 */
	public void setDefaultValue() throws Exception {
		ComboFillAction toValidate = (ComboFillAction) this.inValidation;

	}

}
