package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class RemoveCookiesActionExecutor extends A_BrowserActionExecutor {

	public RemoveCookiesActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
		// TODO Auto-generated constructor stub
	}

	public void execute() throws Exception {
		try {
			super.waitBefore();
			for (String cookieName : ((RemoveCookiesAction) this.action).getCookiesName()) {
				this.driver.manage().deleteCookieNamed(cookieName);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
