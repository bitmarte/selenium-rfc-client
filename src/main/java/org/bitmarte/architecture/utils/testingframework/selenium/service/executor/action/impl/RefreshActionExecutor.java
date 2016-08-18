package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class RefreshActionExecutor extends A_BrowserActionExecutor {

	public RefreshActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void execute() throws Exception {
		try {
			super.waitBefore();
			this.driver.navigate().refresh();
		} catch (Exception e) {
			throw e;
		}
	}

}
