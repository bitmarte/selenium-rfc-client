package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("browser")
public class BrowserConfig {

	@XStreamAlias("mode")
	private String mode;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("arguments")
	private String arguments;

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

}
