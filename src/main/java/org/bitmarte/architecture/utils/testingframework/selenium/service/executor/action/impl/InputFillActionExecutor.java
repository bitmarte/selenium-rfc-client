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
		// TODO Auto-generated constructor stub
	}

	public void execute() throws Exception {
		try {
			super.waitBefore();
			ElementExtractorFactory.getInstance(((InputFillAction) this.action).getElementExtractor())
					.getElements(this.driver, ((InputFillAction) this.action).getElement()).get(0)
					.sendKeys(((InputFillAction) this.action).getValue());
		} catch (Exception e) {
			throw e;
		}
	}

}
