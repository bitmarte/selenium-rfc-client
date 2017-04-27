package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;

/**
 * The concrete ClickActionExecutor implementation
 * 
 * @author bitmarte
 */
public class ClickActionExecutor extends A_BrowserActionExecutor {

	public ClickActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			ElementExtractorFactory.getInstance(((ClickAction) this.action).getElementExtractor())
					.getElement(this.driver, ((ClickAction) this.action).getElement(), null).click();
		} catch (Exception e) {
			throw e;
		}
	}

}
