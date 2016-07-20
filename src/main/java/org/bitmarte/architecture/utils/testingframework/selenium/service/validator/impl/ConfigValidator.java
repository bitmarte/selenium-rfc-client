package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
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
	private static final int MAX_TIMEOUT_PER_MEASURE_IN_SEC = 5;

	public ConfigValidator(Object inValidation) throws Exception {
		super(inValidation);
		// TODO Auto-generated constructor stub
	}

	public void validate() throws Exception {
		Config toValidate = (Config) this.inValidation;

		// checking for required configuration
		if (toValidate.getBrowser() == null) {
			throw new ValidatorException("Property 'browser' is missing!");
		} else {
			if (toValidate.getBrowser().getName() == null) {
				throw new ValidatorException("Property 'name' is missing for browser!");
			}
			if (toValidate.getBrowser().getMode() == null) {
				throw new ValidatorException("Property 'mode' is missing for browser!");
			}
		}

		if (toValidate.getReportBaseDir() == null) {
			throw new ValidatorException("Property 'reportBaseDir' is missing!");
		}

		// checking for allowed values
		try {
			E_BrowserName.valueOf(toValidate.getBrowser().getName());
		} catch (Exception e) {
			throw new ValidatorException("Value '" + toValidate.getBrowser().getName()
					+ "' for property 'name' is not allowed for browser!");
		}
		try {
			E_BrowserMode.valueOf(toValidate.getBrowser().getMode());
		} catch (Exception e) {
			throw new ValidatorException("Value '" + toValidate.getBrowser().getMode()
					+ "' for property 'mode' is not allowed for browser!");
		}

		// checking for restrictions
		if (E_BrowserMode.valueOf(toValidate.getBrowser().getMode()).equals(E_BrowserMode.LOCAL)
				&& E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.IEXPLORER)) {
			throw new ValidatorException("BrowserName 'IEXPLORER' for mode 'LOCAL' is not allowed!");
		}
		if (E_BrowserMode.valueOf(toValidate.getBrowser().getMode()).equals(E_BrowserMode.REMOTE)
				&& toValidate.getSeleniumRcURL() == null) {
			throw new ValidatorException("Property 'seleniumRcURL' for mode 'REMOTE' is required!");
		}
		if (E_BrowserMode.valueOf(toValidate.getBrowser().getMode()).equals(E_BrowserMode.LOCAL)
				&& !E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.FIREFOX)
				&& toValidate.getLocalWebDriverPath() == null) {
			throw new ValidatorException(
					"Property 'localWebDriverPath' for name '" + toValidate.getBrowser().getName() + "' is required!");
		}
		// arguments allowed just for ChromeDriver
		if (toValidate.getBrowser().getArguments() != null
				&& !E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.CHROME)) {
			throw new ValidatorException("Property 'arguments' is supported just for browser " + E_BrowserName.CHROME);
		}

		// checking errorConditions
		if (toValidate.getErrorConditions() == null) {
			throw new ValidatorException("ErrorConditions must not be empty!");
		} else if (toValidate.getErrorConditions().isEmpty()) {
			throw new ValidatorException("ErrorConditions must not be empty!");
		}

		LOG.warn("setCloseBrowserOnFinish = " + toValidate.isCloseBrowserOnFinish());

		// checking for webTimingsAPI
		if (toValidate.getWebTimings() != null) {
			if (E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.IEXPLORER)) {
				throw new ValidatorException(
						"Property 'webTimings' for name '" + toValidate.getBrowser().getName() + "' is not allowed!");
			}
		}
	}

	public void setDefaultValue() throws Exception {
		Config toValidate = (Config) this.inValidation;

		if (toValidate.getMaxTimeOutPerSuccessConditionInSec() <= 0) {
			toValidate.setMaxTimeOutPerSuccessConditionInSec(MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerSuccessConditionInSec = " + MAX_TIMEOUT_PER_SUCCESS_CONDITION_IN_SEC);
		}
		if (toValidate.getMaxTimeOutPerErrorConditionInSec() <= 0) {
			toValidate.setMaxTimeOutPerErrorConditionInSec(MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
			LOG.info("setMaxTimeOutPerErrorConditionInSec = " + MAX_TIMEOUT_PER_ERROR_CONDITION_IN_SEC);
		}
		if (!(StringUtils.endsWith(toValidate.getReportBaseDir(), "/")
				|| StringUtils.endsWith(toValidate.getReportBaseDir(), "\\"))) {
			toValidate.setReportBaseDir(toValidate.getReportBaseDir().concat("/"));
			LOG.warn("Property 'reportBaseDir' must have a slash '\\' or '/' at the end! - I fix it for you from now");
		}

		if (toValidate.getWebTimings() != null) {
			if (toValidate.getWebTimings().getMaxTimeoutPerMeasureInSec() <= 0) {
				toValidate.getWebTimings().setMaxTimeoutPerMeasureInSec(MAX_TIMEOUT_PER_MEASURE_IN_SEC);
				LOG.info("setMaxTimeoutPerMeasureInSec = " + MAX_TIMEOUT_PER_MEASURE_IN_SEC);
			}
		}
	}

}
