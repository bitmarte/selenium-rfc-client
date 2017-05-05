package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This is the browsermob-proxy configuration.
 * 
 * Thanks to browsermob-proxy project
 * [https://github.com/lightbody/browsermob-proxy]
 * 
 * @author bitmarte
 */
@XStreamAlias("mobProxy")
public class MobProxyConfig {

	/**
	 * The port which proxy will start
	 */
	@XStreamAlias("port")
	private int port;

	/**
	 * Limit download rate in order to simulate a slower network connection
	 */
	@XStreamAlias("downloadBytePerSec")
	private long downloadBytePerSec;

	/**
	 * Limit upload rate in order to simulate a slower network connection
	 */
	@XStreamAlias("uploadBytePerSec")
	private long uploadBytePerSec;

	/**
	 * Introducing a network latency, expressed in milliseconds
	 */
	@XStreamAlias("latencyInMillisec")
	private long latencyInMillisec;

	/**
	 * The whole URL of chained proxy, {@link String} format
	 */
	@XStreamAlias("chainedProxy")
	private String chainedProxy;

	/**
	 * Flag for enabling the HAR file caputer of the test
	 */
	@XStreamAlias("enableHarCapture")
	@XStreamAsAttribute
	private String enableHarCapture;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public long getDownloadBytePerSec() {
		return downloadBytePerSec;
	}

	public void setDownloadBytePerSec(long downloadBytePerSec) {
		this.downloadBytePerSec = downloadBytePerSec;
	}

	public long getUploadBytePerSec() {
		return uploadBytePerSec;
	}

	public void setUploadBytePerSec(long uploadBytePerSec) {
		this.uploadBytePerSec = uploadBytePerSec;
	}

	public long getLatencyInMillisec() {
		return latencyInMillisec;
	}

	public void setLatencyInMillisec(long latencyInMillisec) {
		this.latencyInMillisec = latencyInMillisec;
	}

	public String getChainedProxy() {
		return chainedProxy;
	}

	public void setChainedProxy(String chainedProxy) {
		this.chainedProxy = chainedProxy;
	}

	public String isEnableHarCapture() {
		return enableHarCapture;
	}

	public void setEnableHarCapture(String enableHarCapture) {
		this.enableHarCapture = enableHarCapture;
	}

}
