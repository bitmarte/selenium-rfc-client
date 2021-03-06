package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Simulates the browser address bar
 * 
 * @author bitmarte
 */
@XStreamAlias("goTo")
public class GoToUrlAction extends A_BrowserAction {

	/**
	 * The URL
	 */
	@XStreamAlias("url")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
