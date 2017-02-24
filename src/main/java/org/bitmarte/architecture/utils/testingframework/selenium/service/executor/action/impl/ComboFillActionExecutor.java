package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ComboFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author bitmarte
 *
 */
public class ComboFillActionExecutor extends A_BrowserActionExecutor {

	public ComboFillActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			Select select = new Select(
					ElementExtractorFactory.getInstance(((ComboFillAction) this.action).getElementExtractor())
							.getElement(this.driver, ((ComboFillAction) this.action).getElement()));
			select.selectByValue(((ComboFillAction) this.action).getValue());
		} catch (Exception e) {
			throw e;
		}
	}

}
