package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.A_BrowserActionExecutor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.I_BrowserActionExecutor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

/**
 * This is the concrete WindowResizeActionExecutor implementation
 * 
 * @author bitmarte
 */
public class WindowResizeActionExecutor extends A_BrowserActionExecutor {

	public WindowResizeActionExecutor(WebDriver driver, A_BrowserAction browserAction) {
		super(driver, browserAction);
	}

	/**
	 * @see I_BrowserActionExecutor#launcher()
	 */
	public void launcher() throws Exception {
		try {
			if (((WindowResizeAction) this.action).isFullScreen()) {
				this.driver.manage().window().maximize();
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
