package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("removeCookies")
public class RemoveCookiesAction extends A_BrowserAction {

	@XStreamAlias("cookiesName")
	private List<String> cookiesName;

	public List<String> getCookiesName() {
		return cookiesName;
	}

	public void setCookiesName(List<String> cookiesName) {
		this.cookiesName = cookiesName;
	}

}
