package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * @author bitmarte
 *
 */
public class ConfigValidator extends A_Validator {

	private static final int MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC = 10;
	private static final int MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC = 2;

	public ConfigValidator(Object inValidation) throws Exception {
		super(inValidation);
		// TODO Auto-generated constructor stub
	}

	public void validate() throws Exception {
		Config toValidate = (Config) this.inValidation;

		// checking for required configuration
		if (toValidate.getBrowserName() == null) {
			throw new ValidatorException("Property 'browserName' is missing!");
		}
		if (toValidate.getBrowserMode() == null) {
			throw new ValidatorException("Property 'browserMode' is missing!");
		}
		if (toValidate.getReportBaseDir() == null) {
			throw new ValidatorException("Property 'reportBaseDir' is missing!");
		}

		// checking for allowed values
		try {
			E_BrowserName.valueOf(toValidate.getBrowserName());
		} catch (Exception e) {
			throw new ValidatorException(
					"Value '" + toValidate.getBrowserName() + "' for property 'browserName' is not allowed!");
		}
		try {
			E_BrowserMode.valueOf(toValidate.getBrowserMode());
		} catch (Exception e) {
			throw new ValidatorException(
					"Value '" + toValidate.getBrowserMode() + "' for property 'browserMode' is not allowed!");
		}

		// checking for restrictions
		if (E_BrowserMode.valueOf(toValidate.getBrowserMode()).equals(E_BrowserMode.LOCAL)
				&& E_BrowserName.valueOf(toValidate.getBrowserName()).equals(E_BrowserName.IEXPLORER)) {
			throw new ValidatorException("BrowserName 'IEXPLORER' for browserMode 'LOCAL' is not allowed!");
		}
		if (E_BrowserMode.valueOf(toValidate.getBrowserMode()).equals(E_BrowserMode.REMOTE)
				&& toValidate.getSeleniumRcURL() == null) {
			throw new ValidatorException("Property 'seleniumRcURL' for browserMode 'REMOTE' is required!");
		}
		if (E_BrowserMode.valueOf(toValidate.getBrowserMode()).equals(E_BrowserMode.LOCAL)
				&& !E_BrowserName.valueOf(toValidate.getBrowserName()).equals(E_BrowserName.FIREFOX)
				&& toValidate.getLocalWebDriverPath() == null) {
			throw new ValidatorException(
					"Property 'localWebDriverPath' for browserName '" + toValidate.getBrowserName() + "' is required!");
		}
	}

	public void setDefaultValue() throws Exception {
		Config toValidate = (Config) this.inValidation;

		if (toValidate.getMaxTimeOutPerSuccessConditionInSec() == 0) {
			toValidate.setMaxTimeOutPerSuccessConditionInSec(MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerSuccessConditionInSec = " + MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
		}
		if (toValidate.getMaxTimeOutPerErrorConditionInSec() == 0) {
			toValidate.setMaxTimeOutPerErrorConditionInSec(MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerErrorConditionInSec = " + MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
		}
		if (!toValidate.isCloseBrowserOnFinish()) {
			toValidate.setCloseBrowserOnFinish(true);
			LOG.info("setCloseBrowserOnFinish = true");
		}
		if (!(StringUtils.endsWith(toValidate.getReportBaseDir(), "/")
				|| StringUtils.endsWith(toValidate.getReportBaseDir(), "\\"))) {
			toValidate.setReportBaseDir(toValidate.getReportBaseDir().concat("/"));
			LOG.warn("Property 'reportBaseDir' must have a slash '\\' or '/' at the end! - I fix it for you from now");
		}
	}

}
