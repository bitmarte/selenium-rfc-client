package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	protected WebDriverWait wait;

	public A_BrowserActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		this.driver = driver;
		this.action = (A_BrowserAction) browserAction;
	}

	protected void waitBefore(long wait) throws Exception {
		if (wait < 0) {
			if (this.action.getWaitBeforeActionInMillis() != 0) {
				Thread.sleep(this.action.getWaitBeforeActionInMillis());
			} else {
				Thread.sleep(
						SeleniumConfigProvider.getConfig().getBrowserActionExecutor().getWaitBeforeFirstActionInMs());
			}
		} else {
			Thread.sleep(wait);
		}
	}

	public void execute() throws Exception {
		try {
			waitBefore(-1);
			launcher();
		} catch (Exception e) {
			try {
				LOG.info("first executor fails [" + this.action.getClass().getSimpleName() + "], wait "
						+ SeleniumConfigProvider.getConfig().getBrowserActionExecutor().getWaitBeforeRetryActionInMs()
						+ "ms and retry...");
				waitBefore(
						SeleniumConfigProvider.getConfig().getBrowserActionExecutor().getWaitBeforeRetryActionInMs());
				launcher();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

}
