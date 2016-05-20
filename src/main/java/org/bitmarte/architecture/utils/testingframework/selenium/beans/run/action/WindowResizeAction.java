package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("windowResize")
public class WindowResizeAction extends A_BrowserAction {

	@XStreamAlias("widthPx")
	private int widthPx;

	@XStreamAlias("heightPx")
	private int heightPx;

	@XStreamAlias("fullScreen")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "true", "false" })
	private boolean fullScreen;

	public int getWidthPx() {
		return widthPx;
	}

	public void setWidthPx(int widthPx) {
		this.widthPx = widthPx;
	}

	public int getHeightPx() {
		return heightPx;
	}

	public void setHeightPx(int heightPx) {
		this.heightPx = heightPx;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

}
