package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.openqa.selenium.WebDriver;

/**
 * This is the ForwardActionExecutor implementation
 * 
 * @author bitmarte
 */
public class ForwardActionExecutor extends A_BrowserActionExecutor {

	public ForwardActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			this.driver.navigate().forward();
		} catch (Exception e) {
			throw e;
		}
	}

}
