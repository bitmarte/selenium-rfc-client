package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This pojo rappresents the browser's capabilities configuration as a map,
 * key-value
 * 
 * @author bitmarte
 */
@XStreamAlias("browserCapability")
public class BrowserCapabilityConfig {

	/**
	 * Indicates the browser capability type, {@link E_BrowserCapabilityType}
	 */
	@XStreamAlias("capabilityType")
	@XStreamAsAttribute
	private String capabilityType;

	/**
	 * @see DesiredCapabilities for capabilityies name
	 */
	@XStreamAlias("capabilityName")
	private String capabilityName;

	/**
	 * @see DesiredCapabilities and concrete browser's implementation for the
	 *      values
	 */
	@XStreamAlias("capabilityValue")
	private String capabilityValue;

	public String getCapabilityType() {
		return capabilityType;
	}

	public void setCapabilityType(String capabilityType) {
		this.capabilityType = capabilityType;
	}

	public String getCapabilityName() {
		return capabilityName;
	}

	public void setCapabilityName(String capabilityName) {
		this.capabilityName = capabilityName;
	}

	public String getCapabilityValue() {
		return capabilityValue;
	}

	public void setCapabilityValue(String capabilityValue) {
		this.capabilityValue = capabilityValue;
	}

}
