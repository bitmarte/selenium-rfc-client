package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public interface I_ElementExtractor {

	public WebElement getElement(WebDriver driver, String str);
}
