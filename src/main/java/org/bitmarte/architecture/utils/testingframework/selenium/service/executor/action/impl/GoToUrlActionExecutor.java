package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class GoToUrlActionExecutor extends A_BrowserActionExecutor {

	public GoToUrlActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			this.driver.navigate().to(((GoToUrlAction) this.action).getUrl());
		} catch (Exception e) {
			throw e;
		}
	}

}
