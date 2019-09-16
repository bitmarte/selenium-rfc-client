package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.A_TestCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.A_ElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.I_ElementExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * This is the concrete ByIdElementExtractor implementation
 *
 * @author bitmarte
 */
public class ByIdElementExtractor extends A_ElementExtractor {

    /**
     * @see I_ElementExtractor#getElement(WebDriver, String, A_TestCondition)
     */
    public WebElement getElement(WebDriver driver, String str, A_TestCondition condition) {

        Function<WebDriver, WebElement> waiting = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver d) {
                return (WebElement) driver.findElement(By.id(str));
            }
        };

        return super.getWait(driver, str, condition, false).until(waiting);
    }

    /**
     * @see I_ElementExtractor#getElementNoWait(WebDriver, String, A_TestCondition)
     */
    public WebElement getElementNoWait(WebDriver driver, String str, A_TestCondition condition) {

        Function<WebDriver, WebElement> waiting = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver d) {
                return (WebElement) driver.findElement(By.id(str));
            }
        };

        return super.getWait(driver, str, condition, true).until(waiting);
    }

}
