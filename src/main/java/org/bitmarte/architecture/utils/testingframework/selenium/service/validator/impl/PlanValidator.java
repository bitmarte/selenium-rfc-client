package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.InputField;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_InputFieldType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.E_AuthType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.impl.NTLMAuthentication;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class PlanValidator extends A_Validator {

	public PlanValidator(Object inValidation) throws Exception {
		super(inValidation);
		// TODO Auto-generated constructor stub
	}

	public void validate() throws Exception {
		Plan toValidate = (Plan) this.inValidation;

		if (toValidate.getExecutions() == 1) {
			throw new ValidatorException("Attribute 'concurrentExecutors' must be grater than 1!");
		}

		for (Run run : toValidate.getRuns()) {
			if (run.getRunName() == null) {
				throw new ValidatorException("No runName has been specified for current run!");
			}
			if (run.getInputFields() != null) {
				for (InputField field : run.getInputFields()) {
					if (field.getType() != null) {
						try {
							E_InputFieldType.valueOf(field.getType());
						} catch (Exception e) {
							throw new ValidatorException("Type attribute (" + field.getType()
									+ ") at InputFlield is not supported for run '" + run.getRunName() + "'!");
						}
					}
				}
			}
			if (run.getSuccessCondition() == null) {
				throw new ValidatorException(
						"No successCondition has been specified for run '" + run.getRunName() + "'!");
			}
			if (toValidate.isFullscreen()) {
				if (run.getWindowHeightPx() > 0 || run.getWindowHeightPx() > 0) {
					throw new ValidatorException(
							"Fullscreen setup in your plan, no custom window size allowed for run '" + run.getRunName()
									+ "'!");
				}
			}
			if (run.getBrowserActions() != null) {
				for (BrowserAction browserAction : run.getBrowserActions()) {
					if (browserAction.getAction() == null) {
						throw new ValidatorException("No BrowserAction setted for run '" + run.getRunName() + "'!");
					} else {
						try {
							switch (E_BrowserAction.valueOf(browserAction.getAction())) {
							case IFRAME_SWITCH:
							case CLICK:
								if (browserAction.getElementByXPath() == null) {
									throw new ValidatorException(
											"Attribute 'elementByXPath' is required for Action 'IFRAME_SWITCH' and 'CLICK'!");
								}
								break;

							default:
								break;
							}
						} catch (Exception e) {
							throw new ValidatorException(
									"BrowserAction allowed values: '" + E_BrowserAction.values() + "'", e);
						}
					}
				}
			}
			if (run.getAuthentication() != null) {
				if (run.getAuthentication().getAuthType() == null) {
					throw new ValidatorException(
							"Authentication setted, please give me the authType for run '" + run.getRunName() + "'!");
				} else {
					try {
						E_AuthType.valueOf(run.getAuthentication().getAuthType());
					} catch (Exception e) {
						throw new ValidatorException("Value '" + run.getAuthentication().getAuthType()
								+ "' for property 'authType' is not allowed!");
					}
					if (run.getAuthentication().getUsername() == null) {
						throw new ValidatorException("Authentication setted, please give me the username at authType '"
								+ run.getAuthentication().getAuthType() + "' for run '" + run.getRunName() + "'!");
					}
					if (run.getAuthentication().getPassword() == null) {
						throw new ValidatorException("Authentication setted, please give me the password at authType '"
								+ run.getAuthentication().getAuthType() + "' for run '" + run.getRunName() + "'!");
					}
				}
			}
		}
	}

	public void setDefaultValue() throws Exception {
		Plan toValidate = (Plan) this.inValidation;

		for (Run run : toValidate.getRuns()) {
			if (run.getAuthentication() != null) {
				if (run.getAuthentication().getAuthType() != null) {
					if (run.getAuthentication().getWaitPromptInSec() == 0) {
						LOG.info(
								"using default waitPromptInSec time: " + NTLMAuthentication.DEFAULT_WAIT_PROMPT_IN_SEC);
						run.getAuthentication().setWaitPromptInSec(NTLMAuthentication.DEFAULT_WAIT_PROMPT_IN_SEC);
					}
				}
			}
			if (run.getInputFields() != null) {
				for (InputField field : run.getInputFields()) {
					if (field.getType() == null) {
						field.setType(E_InputFieldType.TEXT.name());
					}
				}
			}
		}
	}

}
