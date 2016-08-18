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

	public void execute() throws Exception {
		try {
			super.waitBefore();
			this.driver.navigate().back();
		} catch (Exception e) {
			/*
			 * TODO: quando rilevi un errore nella execute() di ogni Executor
			 * aspetta ripeti la execute() aumentando il
			 * WAIT_BEFORE_ACTION_DEFAULT a un valore più alto da schiantare per
			 * ora
			 */
			throw e;
		}
	}

}
