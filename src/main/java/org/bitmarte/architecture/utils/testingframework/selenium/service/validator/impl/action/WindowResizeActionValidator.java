package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * Validate the window resizer action configuration
 * 
 * @author bitmarte
 */
public class WindowResizeActionValidator extends A_Validator {

	public WindowResizeActionValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bitmarte.architecture.utils.testingframework.selenium.service.
	 * validator.I_Validator#validate()
	 */
	public void validate() throws Exception {
		WindowResizeAction toValidate = (WindowResizeAction) this.inValidation;
		if (toValidate.isFullScreen()) {
			if (toValidate.getWidthPx() != 0 || toValidate.getHeightPx() != 0) {
				throw new ValidatorException(
						"Full screen attribute doesn't want window size, please remove 'widthPx' or 'heightPx' attributes!");
			}
		} else {
			if (toValidate.getWidthPx() <= 0 || toValidate.getHeightPx() <= 0) {
				throw new ValidatorException("Please set 'widthPx' or 'heightPx' attributes!");
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
		WindowResizeAction toValidate = (WindowResizeAction) this.inValidation;

	}

}
