package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import java.util.function.Function;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.A_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author bitmarte
 *
 */
public class ByXpathElementExtractor extends A_ElementExtractor {

	public WebElement getElement(WebDriver driver, String str, A_TestCondition condition) {
		/*
		 * TODO fare in modo che venga accettato un array e che sia possibile
		 * iterare sugli xpath per trovare l'elemento in caso di fallimento.
		 * Occorre modificare l'interfaccia e tutte le altre
		 * implementazioni @I_ElementExtractor
		 */
		long timeoutPerElementExtrator = super.getTimeoutPerElementExtrator(condition);
		WebDriverWait wait = new WebDriverWait(driver, timeoutPerElementExtrator);
		LOG.debug("Serching element '" + str + "' until " + timeoutPerElementExtrator + " sec...");

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver d) {
				return (WebElement) driver.findElement(By.xpath(str));
			}
		});
	}

}
