package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * The generic browser action, abstract one
 * 
 * @author bitmarte
 */
public abstract class A_BrowserAction {

	/**
	 * Waiting timeout before hits the action, expressed in milliseconds
	 */
	@XStreamAlias("waitBeforeActionInMillis")
	@XStreamAsAttribute
	private long waitBeforeActionInMillis;

	public long getWaitBeforeActionInMillis() {
		return waitBeforeActionInMillis;
	}

	public void setWaitBeforeActionInMillis(long waitBeforeActionInMillis) {
		this.waitBeforeActionInMillis = waitBeforeActionInMillis;
	}

}
