package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public interface I_ElementExtractor {

	/**
	 * Extract the {@link WebElement} based on content and, optionally a
	 * {@link A_TestCondition} as configuration
	 * 
	 * @param driver
	 * @param str
	 * @param condition
	 *            null if you don't have a condition
	 * @return
	 */
	public WebElement getElement(WebDriver driver, String str, A_TestCondition condition);

}
