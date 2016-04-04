package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("webTimings")
public class WebTimingsConfig {

	@XStreamAlias("maxTimeoutPerMeasureInSec")
	private int maxTimeoutPerMeasureInSec;

	public int getMaxTimeoutPerMeasureInSec() {
		return maxTimeoutPerMeasureInSec;
	}

	public void setMaxTimeoutPerMeasureInSec(int maxTimeoutPerMeasureInSec) {
		this.maxTimeoutPerMeasureInSec = maxTimeoutPerMeasureInSec;
	}

}
