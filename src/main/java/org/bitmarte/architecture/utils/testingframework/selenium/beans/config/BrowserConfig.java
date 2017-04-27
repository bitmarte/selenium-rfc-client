package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * This is the browser configuration
 * 
 * @author bitmarte
 */
@XStreamAlias("browser")
public class BrowserConfig {

	/**
	 * The test approach, {@link E_BrowserMode}
	 */
	@XStreamAlias("mode")
	private String mode;

	/**
	 * The browser, {@link E_BrowserName}
	 */
	@XStreamAlias("name")
	private String name;

	/**
	 * This is an optional node where put the webdriver arguments, just Chrome
	 * and Firefox supported
	 * (http://peter.sh/experiments/chromium-command-line-switches/)
	 */
	@XStreamAlias("arguments")
	private String arguments;

	/**
	 * The list of {@link BrowserCapabilityConfig}
	 */
	@XStreamImplicit
	private List<BrowserCapabilityConfig> browserCapabilities;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public List<BrowserCapabilityConfig> getBrowserCapabilities() {
		return browserCapabilities;
	}

	public void setBrowserCapabilities(List<BrowserCapabilityConfig> browserCapabilities) {
		this.browserCapabilities = browserCapabilities;
	}

}
