package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.E_AuthType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This class allows you to sign-in with different authentications,
 * {@link E_AuthType}
 * 
 * @author bitmarte
 */
@XStreamAlias("authentication")
public class Authentication {

	/**
	 * @see E_AuthType
	 */
	@XStreamAlias("authType")
	@XStreamAsAttribute
	private String authType;

	/**
	 * Waiting timeout in order to attempt the promt, if the authetication type
	 * required it
	 */
	@XStreamAlias("waitPromptInSec")
	@XStreamAsAttribute
	private int waitPromptInSec;

	/**
	 * The username
	 */
	@XStreamAlias("username")
	private String username;

	/**
	 * The password
	 */
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
