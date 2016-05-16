package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("authentication")
public class Authentication {

	@XStreamAlias("authType")
	@XStreamAsAttribute
	private String authType;

	@XStreamAlias("waitPromptInSec")
	@XStreamAsAttribute
	private int waitPromptInSec;

	@XStreamAlias("username")
	private String username;

	@XStreamAlias("password")
	private String password;

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public int getWaitPromptInSec() {
		return waitPromptInSec;
	}

	public void setWaitPromptInSec(int waitPromptInSec) {
		this.waitPromptInSec = waitPromptInSec;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
