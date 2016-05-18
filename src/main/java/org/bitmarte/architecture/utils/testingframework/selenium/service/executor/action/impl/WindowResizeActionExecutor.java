package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

/**
 * @author bitmarte
 *
 */
public class WindowResizeActionExecutor extends A_BrowserActionExecutor {

	public WindowResizeActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
		// TODO Auto-generated constructor stub
	}

	public void execute() throws Exception {
		try {
			super.waitBefore();
			if (((WindowResizeAction) this.action).isFullScreen()) {
				this.driver.manage().window().fullscreen();
			} else {
				this.driver.manage().window().setPosition(new Point(0, 0));
				Dimension d = new Dimension(((WindowResizeAction) this.action).getWidthPx(),
						((WindowResizeAction) this.action).getHeightPx());
				this.driver.manage().window().setSize(d);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
