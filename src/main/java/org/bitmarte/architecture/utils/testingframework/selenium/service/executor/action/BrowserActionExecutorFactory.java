package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.BackAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ComboFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ForwardAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.IFrameSwitchAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RadioFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RefreshAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveAllCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.exceptions.BrowserActionExecutorException;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.BackActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.ClickActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.ComboFillActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.ForwardActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.GoToUrlActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.IFrameSwitchActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.InputFillActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.RadioFillActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.RefreshActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.RemoveAllCookiesExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.RemoveCookiesActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl.WindowResizeActionExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class BrowserActionExecutorFactory {

	private static final Logger LOG = LoggerFactory.getLogger(BrowserActionExecutorFactory.class);

	public static I_BrowserActionExecutor getInstance(WebDriver driver, A_BrowserAction browserAction)
			throws Exception {

		LOG.info("Executing browser action '" + browserAction.getClass().getCanonicalName().substring(
				browserAction.getClass().getCanonicalName().lastIndexOf(".") + 1,
				browserAction.getClass().getCanonicalName().length()) + "' ...");
		try {
			if (browserAction instanceof BackAction) {
				return new BackActionExecutor(driver, (BackAction) browserAction);
			} else if (browserAction instanceof GoToUrlAction) {
				return new GoToUrlActionExecutor(driver, (GoToUrlAction) browserAction);
			} else if (browserAction instanceof ClickAction) {
				return new ClickActionExecutor(driver, (ClickAction) browserAction);
			} else if (browserAction instanceof ComboFillAction) {
				return new ComboFillActionExecutor(driver, (ComboFillAction) browserAction);
			} else if (browserAction instanceof ForwardAction) {
				return new ForwardActionExecutor(driver, (ForwardAction) browserAction);
			} else if (browserAction instanceof IFrameSwitchAction) {
				return new IFrameSwitchActionExecutor(driver, (IFrameSwitchAction) browserAction);
			} else if (browserAction instanceof InputFillAction) {
				return new InputFillActionExecutor(driver, (InputFillAction) browserAction);
			} else if (browserAction instanceof RadioFillAction) {
				return new RadioFillActionExecutor(driver, (RadioFillAction) browserAction);
			} else if (browserAction instanceof RefreshAction) {
				return new RefreshActionExecutor(driver, (RefreshAction) browserAction);
			} else if (browserAction instanceof RemoveAllCookiesAction) {
				return new RemoveAllCookiesExecutor(driver, (RemoveAllCookiesAction) browserAction);
			} else if (browserAction instanceof RemoveCookiesAction) {
				return new RemoveCookiesActionExecutor(driver, (RemoveCookiesAction) browserAction);
			} else if (browserAction instanceof WindowResizeAction) {
				return new WindowResizeActionExecutor(driver, (WindowResizeAction) browserAction);
			}
		} catch (Exception e) {
			throw e;
		}

		throw new BrowserActionExecutorException("Error on BrowserActionExecutorFactory.getInstance()!");
	}
}
