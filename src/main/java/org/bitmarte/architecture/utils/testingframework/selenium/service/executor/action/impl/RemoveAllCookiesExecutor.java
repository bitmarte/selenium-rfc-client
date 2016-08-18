package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class RemoveAllCookiesExecutor extends A_BrowserActionExecutor {

	public RemoveAllCookiesExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void execute() throws Exception {
		try {
			super.waitBefore();
			this.driver.manage().deleteAllCookies();
		} catch (Exception e) {
			throw e;
		}
	}

}
