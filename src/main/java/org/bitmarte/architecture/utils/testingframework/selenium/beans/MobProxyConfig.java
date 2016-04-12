package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("mobProxy")
public class MobProxyConfig {

	@XStreamAlias("port")
	private int port;

	@XStreamAlias("downloadBytePerSec")
	private long downloadBytePerSec;

	@XStreamAlias("uploadBytePerSec")
	private long uploadBytePerSec;

	@XStreamAlias("latencyInMillisec")
	private long latencyInMillisec;

	@XStreamAlias("chainedProxy")
	private String chainedProxy;

	@XStreamAlias("enableHarCapture")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "true", "false" })
	@XStreamAsAttribute
	private boolean enableHarCapture;

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

	public boolean isEnableHarCapture() {
		return enableHarCapture;
	}

	public void setEnableHarCapture(boolean enableHarCapture) {
		this.enableHarCapture = enableHarCapture;
	}

}
