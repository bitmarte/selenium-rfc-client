package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_BrowserActionExecutor implements I_BrowserActionExecutor {

	private static final long WAIT_BEFORE_ACTION_DEFAULT = 300;

	protected static Logger LOG = LoggerFactory.getLogger(A_BrowserActionExecutor.class);

	protected WebDriver driver;
	protected A_BrowserAction action;
	protected WebDriverWait wait;

	public A_BrowserActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		this.driver = driver;
		this.action = (A_BrowserAction) browserAction;
	}

	protected void waitBefore() throws Exception {
		if (this.action.getWaitBeforeActionInMillis() > WAIT_BEFORE_ACTION_DEFAULT) {
			Thread.sleep(this.action.getWaitBeforeActionInMillis());
		} else {
			/*
			 * TODO: rendere configurabile WAIT_BEFORE_ACTION_DEFAULT da
			 * config.xml con un default impostato dal validator del config
			 */
			Thread.sleep(WAIT_BEFORE_ACTION_DEFAULT);
		}
	}

}
