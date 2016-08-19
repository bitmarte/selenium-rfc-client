package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.A_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	public List<WebElement> getElements(WebDriver driver, String str) {
		WebDriverWait wait = new WebDriverWait(driver, super.getTimeoutPerElementExtrator());
		LOG.debug("Serching element '" + str + "' unit " + super.getTimeoutPerElementExtrator() + " ms...");
		final String el = str;
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				List<WebElement> elements = d.findElements(By.xpath(el));
				if (!elements.isEmpty()) {
					return true;
				}
				return false;
			}
		});

		return driver.findElements(By.xpath(str));
	}

}
