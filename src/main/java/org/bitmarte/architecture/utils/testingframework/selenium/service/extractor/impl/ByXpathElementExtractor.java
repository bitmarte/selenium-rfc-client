package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.A_ElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.I_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * This is the concrete ByXpathElementExtractor implementation
 * 
 * @author bitmarte
 */
public class ByXpathElementExtractor extends A_ElementExtractor {

	/**
	 * @see I_ElementExtractor#getElement(WebDriver, String, A_TestCondition)
	 */
	public WebElement getElement(WebDriver driver, String str, A_TestCondition condition) {
		/*
		 * TODO fare in modo che venga accettato un array e che sia possibile
		 * iterare sugli xpath per trovare l'elemento in caso di fallimento.
		 * Occorre modificare l'interfaccia e tutte le altre
		 * implementazioni @I_ElementExtractor
		 */
		long timeoutPerElementExtrator = super.getTimeoutPerElementExtrator(condition);
		long pollingPerElementExtrator = super.getPollingPerElementExtractor();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(pollingPerElementExtrator, TimeUnit.MILLISECONDS);
		wait.withTimeout(timeoutPerElementExtrator, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		LOG.debug("Serching element '" + str + "' until " + timeoutPerElementExtrator + " sec every "
				+ pollingPerElementExtrator + " msec...");

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver d) {
				return (WebElement) driver.findElement(By.xpath(str));
			}
		});
	}

}
