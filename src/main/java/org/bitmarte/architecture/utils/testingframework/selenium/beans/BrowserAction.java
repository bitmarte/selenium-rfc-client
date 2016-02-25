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

	@XStreamAlias("elementByXPath")
	@XStreamAsAttribute
	private String elementByXPath;

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

}
