package org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.impl;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.A_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public class ByClassNameElementExtractor extends A_ElementExtractor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor
	 * .I_ElementExtractor#getElements(org.openqa.selenium.WebDriver,
	 * java.lang.String)
	 */
	public List<WebElement> getElements(WebDriver driver, String str) {
		return driver.findElements(By.className(str));
	}

}
