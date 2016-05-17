package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("browserAction")
public class BrowserAction {

	@XStreamAlias("action")
	private String action;

	@XStreamAlias("elementByXPath")
	private String elementByXPath;

	@XStreamAlias("firstAction")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "true", "false" })
	@XStreamAsAttribute
	private boolean firstAction;

	@XStreamAlias("waitBeforeActionInMillis")
	@XStreamAsAttribute
	private long waitBeforeActionInMillis;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getElementByXPath() {
		return elementByXPath;
	}

	public void setElementByXPath(String elementByXPath) {
		this.elementByXPath = elementByXPath;
	}

	public boolean isFirstAction() {
		return firstAction;
	}

	public void setFirstAction(boolean firstAction) {
		this.firstAction = firstAction;
	}

	public long getWaitBeforeActionInMillis() {
		return waitBeforeActionInMillis;
	}

	public void setWaitBeforeActionInMillis(long waitBeforeActionInMillis) {
		this.waitBeforeActionInMillis = waitBeforeActionInMillis;
	}

}
