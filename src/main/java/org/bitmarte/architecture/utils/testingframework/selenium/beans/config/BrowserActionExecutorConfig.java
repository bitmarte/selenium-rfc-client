package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("browserActionExecutor")
public class BrowserActionExecutorConfig {

	@XStreamAlias("waitBeforeFirstActionInMs")
	private long waitBeforeFirstActionInMs;

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
