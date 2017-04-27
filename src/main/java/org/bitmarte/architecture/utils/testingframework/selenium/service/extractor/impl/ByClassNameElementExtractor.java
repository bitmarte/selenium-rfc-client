package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import java.util.function.Function;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.A_ElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.I_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is the concrete ByClassNameElementExtractor implementation
 * 
 * @author bitmarte
 */
public class ByClassNameElementExtractor extends A_ElementExtractor {

	/**
	 * @see I_ElementExtractor#getElement(WebDriver, String, A_TestCondition)
	 */
	public WebElement getElement(WebDriver driver, String str, A_TestCondition condition) {
		long timeoutPerElementExtrator = super.getTimeoutPerElementExtrator(condition);
		WebDriverWait wait = new WebDriverWait(driver, timeoutPerElementExtrator);
		LOG.debug("Serching element '" + str + "' until " + timeoutPerElementExtrator + " sec...");

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver d) {
				return (WebElement) d.findElement(By.className(str));
			}
		});
	}

}
