package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This class allows you to remove a specific set of cookies
 * 
 * @author bitmarte
 */
@XStreamAlias("removeCookies")
public class RemoveCookiesAction extends A_BrowserAction {

	/**
	 * The list of cookies, {@link String}
	 */
	@XStreamAlias("cookiesName")
	private List<String> cookiesName;

	public List<String> getCookiesName() {
		return cookiesName;
	}

	public void setCookiesName(List<String> cookiesName) {
		this.cookiesName = cookiesName;
	}

}
