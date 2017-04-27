package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;

/**
 * This is the concrete InputFillActionExecutor implementation
 * 
 * @author bitmarte
 */
public class InputFillActionExecutor extends A_BrowserActionExecutor {

	public InputFillActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			ElementExtractorFactory.getInstance(((InputFillAction) this.action).getElementExtractor())
					.getElement(this.driver, ((InputFillAction) this.action).getElement(), null)
					.sendKeys(((InputFillAction) this.action).getValue());
		} catch (Exception e) {
			throw e;
		}
	}

}
