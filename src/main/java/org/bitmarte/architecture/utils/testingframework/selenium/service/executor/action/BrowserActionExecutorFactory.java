package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.BackAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.exceptions.BrowserActionExecutorException;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.BackActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.GoToUrlActionExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class BrowserActionExecutorFactory {

	private static final Logger LOG = LoggerFactory.getLogger(BrowserActionExecutorFactory.class);

	public static I_BrowserActionExecutor getInstance(WebDriver driver, Object browserAction) throws Exception {

		if (browserAction instanceof BackAction) {
			return new BackActionExecutor(driver, (BackAction) browserAction);
		} else if (browserAction instanceof GoToUrlAction) {
			return new GoToUrlActionExecutor(driver, (GoToUrlAction) browserAction);
		}

		throw new BrowserActionExecutorException("Error on BrowserActionExecutorFactory.getInstance()!");
	}
}
