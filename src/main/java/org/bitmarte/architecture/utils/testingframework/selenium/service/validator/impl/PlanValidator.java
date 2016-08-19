package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.E_AuthType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.impl.NTLMAuthentication;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class PlanValidator extends A_Validator {

	public PlanValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	public void validate() throws Exception {
		Plan toValidate = (Plan) this.inValidation;

		if (toValidate.getExecutions() == 1) {
			throw new ValidatorException("Attribute 'concurrentExecutors' must be grater than 1!");
		}

		for (Run run : toValidate.getRuns()) {
			if (run.getBrowserActions() == null) {
				throw new ValidatorException("No browserActions has been specified for current run!");
			} else {
				if (run.getBrowserActions().isEmpty()) {
					throw new ValidatorException("No browserActions has been specified for current run!");
				}
			}
			if (run.getRunName() == null) {
				throw new ValidatorException("No runName has been specified for current run!");
			}
			if (run.getSuccessCondition() == null) {
				throw new ValidatorException(
						"No successCondition has been specified for run '" + run.getRunName() + "'!");
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

			// validate BrowserActions
			for (A_BrowserAction browserAction : run.getBrowserActions()) {
				ValidatorHandler.execute(browserAction);
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
		}
	}

}
