package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the generic DOM Element extractor implementation
 * 
 * @author bitmarte
 */
public abstract class A_ElementExtractor implements I_ElementExtractor {

	protected static final Logger LOG = LoggerFactory.getLogger(A_ElementExtractor.class);

	/**
	 * Retrieves the waiting time before hits the element extractor, expressed
	 * in milliseconds
	 * 
	 * @param condition
	 *            {@link A_TestCondition}
	 * @return the waiting time before hits the element extractor, expressed in
	 *         milliseconds
	 */
	protected long getTimeoutPerElementExtrator(A_TestCondition condition) {
		long timeoutPerElementExtrator = SeleniumConfigProvider.getConfig().getMaxTimeOutPerElementExtratorInSec();

		if (condition != null && condition.getMaxTimeOutPerElementExtratorInSec() > 0) {
			timeoutPerElementExtrator = condition.getMaxTimeOutPerElementExtratorInSec();
		}

		return timeoutPerElementExtrator;
	}

	/**
	 * Retrieves the polling in millesec, during element extractor
	 * 
	 * @return time for polling in millesec, during element extractor
	 */
	protected long getPollingPerElementExtractor() {
		return SeleniumConfigProvider.getConfig().getPollingPerElementExtractorInMillisec();
	}

}
