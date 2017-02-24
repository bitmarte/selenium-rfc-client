package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class InputFillActionExecutor extends A_BrowserActionExecutor {

	public InputFillActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			ElementExtractorFactory.getInstance(((InputFillAction) this.action).getElementExtractor())
					.getElement(this.driver, ((InputFillAction) this.action).getElement())
					.sendKeys(((InputFillAction) this.action).getValue());
		} catch (Exception e) {
			throw e;
		}
	}

}
