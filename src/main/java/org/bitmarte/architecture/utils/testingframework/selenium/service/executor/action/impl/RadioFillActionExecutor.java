package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RadioFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public class RadioFillActionExecutor extends A_BrowserActionExecutor {

	public RadioFillActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			WebElement el = ElementExtractorFactory.getInstance(((RadioFillAction) this.action).getElementExtractor())
					.getElement(this.driver, ((RadioFillAction) this.action).getElement());
			if (el.getText().equals(((RadioFillAction) this.action).getValue())) {
				el.click();
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
