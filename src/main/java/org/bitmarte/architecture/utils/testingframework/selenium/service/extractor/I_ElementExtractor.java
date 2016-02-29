package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author bitmarte
 *
 */
public interface I_ElementExtractor {

	public List<WebElement> getElements(WebDriver driver, String str);
}
