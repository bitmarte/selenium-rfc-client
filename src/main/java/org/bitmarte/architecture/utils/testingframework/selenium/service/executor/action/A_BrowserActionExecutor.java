package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_BrowserActionExecutor implements I_BrowserActionExecutor {

	protected static Logger LOG = LoggerFactory.getLogger(A_BrowserActionExecutor.class);

	protected WebDriver driver;
	protected A_BrowserAction action;

	public A_BrowserActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		this.driver = driver;
		this.action = (A_BrowserAction) browserAction;
	}

	protected void waitBefore() throws Exception {
		if (this.action.getWaitBeforeActionInMillis() >= 1) {
			this.driver.wait(this.action.getWaitBeforeActionInMillis());
		}
	}

}
