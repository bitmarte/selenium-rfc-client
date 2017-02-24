package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import java.util.function.Function;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.
	 * I_ElementExtractor#getElements (org.openqa.selenium.WebDriver,
	 * java.lang.String)
	 */
	public WebElement getElement(WebDriver driver, String str) {
		/*
		 * TODO fare in modo che venga accettato un array e che sia possibile
		 * iterare sugli xpath per trovare l'elemento in caso di fallimento.
		 * Occorre modificare l'interfaccia e tutte le altre
		 * implementazioni @I_ElementExtractor
		 */
		WebDriverWait wait = new WebDriverWait(driver, super.getTimeoutPerElementExtrator());
		LOG.debug("Serching element '" + str + "' unit " + super.getTimeoutPerElementExtrator() + " sec...");

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver d) {
				return (WebElement) driver.findElement(By.xpath(str));
			}
		});
	}

}
