package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ScrollAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public class ScrollActionExecutor extends A_BrowserActionExecutor {

	public ScrollActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	public void launcher() throws Exception {
		try {
			// no element -> window scroll
			if (((ScrollAction) this.action).getElement() == null) {
				LOG.debug("window.scrollBy(" + ((ScrollAction) this.action).getLeftPx() + ","
						+ ((ScrollAction) this.action).getTopPx() + ")");
				((JavascriptExecutor) this.driver)
						.executeScript("window.scrollBy(" + ((ScrollAction) this.action).getLeftPx() + ","
								+ ((ScrollAction) this.action).getTopPx() + ")", "");
			} else {
				LOG.debug("scroll element in page [" + ((ScrollAction) this.action).getElement() + "] "
						+ ((ScrollAction) this.action).getLeftPx() + "," + ((ScrollAction) this.action).getTopPx());
				WebElement el = ElementExtractorFactory.getInstance(((ScrollAction) this.action).getElementExtractor())
						.getElement(this.driver, ((ScrollAction) this.action).getElement(), null);
				((JavascriptExecutor) this.driver)
						.executeScript("arguments[0].scrollTop = " + ((ScrollAction) this.action).getTopPx(), el, "");
				((JavascriptExecutor) this.driver)
						.executeScript("arguments[0].scrollLeft = " + ((ScrollAction) this.action).getLeftPx(), el, "");
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
