package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.DoubleClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * The concrete DoubleClickActionExecutor implementation
 * 
 * @author bitmarte
 */
public class DoubleClickActionExecutor extends A_BrowserActionExecutor {

	public DoubleClickActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			Actions actions = new Actions(this.driver);
			actions.doubleClick(
					ElementExtractorFactory.getInstance(((DoubleClickAction) this.action).getElementExtractor())
							.getElement(this.driver, ((DoubleClickAction) this.action).getElement(), null));
			actions.perform();
		} catch (Exception e) {
			throw e;
		}
	}

}
