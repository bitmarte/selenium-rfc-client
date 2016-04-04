package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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

}
