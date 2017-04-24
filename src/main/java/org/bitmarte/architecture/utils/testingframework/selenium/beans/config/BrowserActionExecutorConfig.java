package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This class configure the action executor in order to specify a human behavior
 * 
 * @author bitmarte
 */
@XStreamAlias("browserActionExecutor")
public class BrowserActionExecutorConfig {

	/**
	 * Waiting timeout before hit the first time action, expressed in
	 * milliseconds
	 */
	@XStreamAlias("waitBeforeFirstActionInMs")
	private long waitBeforeFirstActionInMs;

	/**
	 * Waiting timeout on retry action, expressed in milliseconds
	 */
	@XStreamAlias("waitBeforeRetryActionInMs")
	private long waitBeforeRetryActionInMs;

	public long getWaitBeforeFirstActionInMs() {
		return waitBeforeFirstActionInMs;
	}

	public void setWaitBeforeFirstActionInMs(long waitBeforeFirstActionInMs) {
		this.waitBeforeFirstActionInMs = waitBeforeFirstActionInMs;
	}

	public long getWaitBeforeRetryActionInMs() {
		return waitBeforeRetryActionInMs;
	}

	public void setWaitBeforeRetryActionInMs(long waitBeforeRetryActionInMs) {
		this.waitBeforeRetryActionInMs = waitBeforeRetryActionInMs;
	}

}
