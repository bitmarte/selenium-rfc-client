package org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserActionExecutorConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserCapabilityConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserCapabilityType;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.E_PlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.A_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.I_Validator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;

/**
 * This is the concrete config validator
 * 
 * @author bitmarte
 */
public class ConfigValidator extends A_Validator {

	private static final int MAX_TIMEOUT_PER_MEASURE_IN_SEC = 5;
	private static final int MAX_TIMEOUT_PER_ELEMENT_EXTRACTOR_IN_SEC = 5;
	private static final long POLLING_PER_ELEMENT_EXTRACTOR_IN_MILLISEC = 250;
	private static final long WAIT_BEFORE_ACTION_FIRST_EXEC_IN_MS = 100;
	private static final long WAIT_BEFORE_ACTION_RETRY_EXEC_IN_MS = 300;

	public ConfigValidator(Object inValidation) throws Exception {
		super(inValidation);
	}

	/**
	 * @see I_Validator#validate()
	 */
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

		if (toValidate.getLocalWebDriverPath() == null) {
			throw new ValidatorException("Property 'localWebDriverPath' is missing!");
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
				&& E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.IEXPLORER)) {
			throw new ValidatorException(
					"Property 'arguments' is not supported for browser " + E_BrowserName.IEXPLORER);
		}

		// checking errorConditions
		if (toValidate.getErrorConditions() == null) {
			throw new ValidatorException("ErrorConditions must not be empty!");
		} else if (toValidate.getErrorConditions().isEmpty()) {
			throw new ValidatorException("ErrorConditions must not be empty!");
		}

		// checking for webTimingsAPI
		if (toValidate.getWebTimings() != null) {
			if (E_BrowserName.valueOf(toValidate.getBrowser().getName()).equals(E_BrowserName.IEXPLORER)) {
				throw new ValidatorException(
						"Property 'webTimings' for name '" + toValidate.getBrowser().getName() + "' is not allowed!");
			}
		}

		// checking for customPlanLoader
		if (toValidate.getCustomPlanLoaders() != null && !toValidate.getCustomPlanLoaders().isEmpty()) {
			for (String customPlanLoader : toValidate.getCustomPlanLoaders()) {
				try {
					switch (E_PlanLoader.valueOf(customPlanLoader)) {
					case DEFAULT_XML:
						throw new ValidatorException(
								"Property 'customPlanLoader' for name '" + customPlanLoader + "' is not allowed!");
					default:
						LOG.debug("allowed customPlanLoader: " + customPlanLoader);
						break;
					}
				} catch (Exception e) {
					throw new ValidatorException("Property 'customPlanLoader' for name '" + customPlanLoader
							+ "' is unknown, please check E_PlanLoader!");
				}
			}
		}

		// checking E_BrowserCapabilityType
		if (toValidate.getBrowser().getBrowserCapabilities() != null) {
			for (BrowserCapabilityConfig capability : toValidate.getBrowser().getBrowserCapabilities()) {
				try {
					E_BrowserCapabilityType.valueOf(capability.getCapabilityType());
				} catch (Exception e) {
					throw new ValidatorException(
							"Property 'capabilityType' for browserCapability list must be BOOLEAN or STRING!");
				}
			}
		}

		// closeBrowserOnFinish
		if (!StringUtils.equalsAny(toValidate.isCloseBrowserOnFinish(), "true", "false")) {
			throw new ValidatorException("Property 'closeBrowserOnFinish' must be a BOOLEAN value (true/false)!");
		}
		// cleanReportBaseDirOnStart
		if (!StringUtils.equalsAny(toValidate.isCleanReportBaseDirOnStart(), "true", "false")) {
			throw new ValidatorException("Property 'cleanReportBaseDirOnStart' must be a BOOLEAN value (true/false)!");
		}
		// inViewScreenshot
		if (!StringUtils.equalsAny(toValidate.isInViewScreenshot(), "true", "false")) {
			throw new ValidatorException("Property 'inViewScreenshot' must be a BOOLEAN value (true/false)!");
		}
		// enableHarCapture
		if (!StringUtils.equalsAny(toValidate.getMobProxy().isEnableHarCapture(), "true", "false")) {
			throw new ValidatorException(
					"Property 'enableHarCapture' for mobProxy must be a BOOLEAN value (true/false)!");
		}

	}

	/**
	 * Setting default values for configuration
	 * 
	 * @see I_Validator#setDefaultValue()
	 */
	public void setDefaultValue() throws Exception {
		Config toValidate = (Config) this.inValidation;

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

		if (toValidate.getCustomPlanLoaders() == null || toValidate.getCustomPlanLoaders().isEmpty()) {
			toValidate.setCustomPlanLoaders(new ArrayList<String>());
		} else {
			LOG.debug("setting default PlanLoader " + E_PlanLoader.DEFAULT_XML.name());
			toValidate.getCustomPlanLoaders().add(0, E_PlanLoader.DEFAULT_XML.name());
		}

		// BrowserActionExecutorConfig
		if (toValidate.getBrowserActionExecutor() == null) {
			toValidate.setBrowserActionExecutor(new BrowserActionExecutorConfig());
		}
		if (toValidate.getBrowserActionExecutor().getWaitBeforeFirstActionInMs() == 0) {
			toValidate.getBrowserActionExecutor().setWaitBeforeFirstActionInMs(WAIT_BEFORE_ACTION_FIRST_EXEC_IN_MS);
			LOG.info("setWaitBeforeFirstActionInMs = " + WAIT_BEFORE_ACTION_FIRST_EXEC_IN_MS);
		}
		if (toValidate.getBrowserActionExecutor().getWaitBeforeRetryActionInMs() == 0) {
			toValidate.getBrowserActionExecutor().setWaitBeforeRetryActionInMs(WAIT_BEFORE_ACTION_RETRY_EXEC_IN_MS);
			LOG.info("setWaitBeforeRetryActionInMs = " + WAIT_BEFORE_ACTION_RETRY_EXEC_IN_MS);
		}

		// MaxTimeoutPerElementExtractor
		if (toValidate.getMaxTimeOutPerElementExtratorInSec() == 0) {
			toValidate.setMaxTimeOutPerElementExtratorInSec(MAX_TIMEOUT_PER_ELEMENT_EXTRACTOR_IN_SEC);
			LOG.info("setMaxTimeOutPerElementExtratorInSec = " + MAX_TIMEOUT_PER_ELEMENT_EXTRACTOR_IN_SEC);
		}

		// pollingPerElementExtractor
		if (toValidate.getPollingPerElementExtractorInMillisec() == 0) {
			toValidate.setPollingPerElementExtractorInMillisec(POLLING_PER_ELEMENT_EXTRACTOR_IN_MILLISEC);
			LOG.info("setMaxTimeOutPerElementExtratorInSec = " + POLLING_PER_ELEMENT_EXTRACTOR_IN_MILLISEC);
		}

		// closeBrowserOnFinish
		if (toValidate.isCloseBrowserOnFinish() == null) {
			toValidate.setCloseBrowserOnFinish("true");
			LOG.info("isCloseBrowserOnFinish = true");
		}

		// cleanReportBaseDirOnStart
		if (toValidate.isCleanReportBaseDirOnStart() == null) {
			toValidate.setCleanReportBaseDirOnStart("true");
			LOG.info("isCleanReportBaseDirOnStart = true");
		}

		// inViewScreenshot
		if (toValidate.isInViewScreenshot() == null) {
			toValidate.setInViewScreenshot("true");
			LOG.info("isInViewScreenshot = true");
		}

		// enableHarCapture
		if (toValidate.getMobProxy().isEnableHarCapture() == null) {
			toValidate.getMobProxy().setEnableHarCapture("false");
			LOG.info("isEnableHarCapture = false");
		}

	}

}
