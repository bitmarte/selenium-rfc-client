package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * This is the concrete RemoveCookiesActionExecutor implementation
 * 
 * @author bitmarte
 */
public class RemoveCookiesActionExecutor extends A_BrowserActionExecutor {

	public RemoveCookiesActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			for (String cookieName : ((RemoveCookiesAction) this.action).getCookiesName()) {
				this.driver.manage().deleteCookieNamed(cookieName);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
