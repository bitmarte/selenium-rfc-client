package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import java.time.Duration;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
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
	 * Retrieves the fluent wait for element extractor
	 * 
	 * @param driver    the {@link WebDriver}
	 * @param str       the element identifier
	 * @param condition the generic test condition for override configurations
	 *                  {@link A_TestCondition}
	 * @return
	 */
	protected FluentWait<WebDriver> getWait(WebDriver driver, String str, A_TestCondition condition) {
		long timeoutPerElementExtrator = this.getTimeoutPerElementExtrator(condition);
		long pollingPerElementExtrator = this.getPollingPerElementExtractor();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofMillis(timeoutPerElementExtrator));
		wait.withTimeout(Duration.ofSeconds(timeoutPerElementExtrator));
		wait.ignoring(NoSuchElementException.class);

		LOG.debug("Serching element '" + str + "' until " + timeoutPerElementExtrator + " sec every "
				+ pollingPerElementExtrator + " msec...");

		return wait;
	}

	/**
	 * Retrieves the waiting time before hits the element extractor, expressed in
	 * milliseconds
	 * 
	 * @param condition {@link A_TestCondition}
	 * @return the waiting time before hits the element extractor, expressed in
	 *         milliseconds
	 */
	private long getTimeoutPerElementExtrator(A_TestCondition condition) {
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
	private long getPollingPerElementExtractor() {
		return SeleniumConfigProvider.getConfig().getPollingPerElementExtractorInMillisec();
	}

}
