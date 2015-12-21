package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("browserAction")
public class BrowserAction {

	@XStreamAlias("action")
	@XStreamAsAttribute
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
