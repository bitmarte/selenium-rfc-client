package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.IFrameSwitchAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.WebDriver;

/**
 * This is the concrete IFrameSwitchActionExecutor implementation
 * 
 * @author bitmarte
 */
public class IFrameSwitchActionExecutor extends A_BrowserActionExecutor {

	public IFrameSwitchActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			this.driver.switchTo()
					.frame(ElementExtractorFactory.getInstance(((IFrameSwitchAction) this.action).getElementExtractor())
							.getElement(this.driver, ((IFrameSwitchAction) this.action).getElement(), null));
		} catch (Exception e) {
			throw e;
		}
	}

}
