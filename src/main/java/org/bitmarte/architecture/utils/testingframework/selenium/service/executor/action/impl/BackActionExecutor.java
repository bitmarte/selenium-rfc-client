package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class BackActionExecutor extends A_BrowserActionExecutor {

	public BackActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			this.driver.navigate().back();
		} catch (Exception e) {
			throw e;
		}
	}

}
