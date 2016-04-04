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

	@XStreamAlias("kpiIntervalMeasureInSec")
	private int kpiIntervalMeasureInSec;

	public int getMaxTimeoutPerMeasureInSec() {
		return maxTimeoutPerMeasureInSec;
	}

	public void setMaxTimeoutPerMeasureInSec(int maxTimeoutPerMeasureInSec) {
		this.maxTimeoutPerMeasureInSec = maxTimeoutPerMeasureInSec;
	}

	public int getKpiIntervalMeasureInSec() {
		return kpiIntervalMeasureInSec;
	}

	public void setKpiIntervalMeasureInSec(int kpiIntervalMeasureInSec) {
		this.kpiIntervalMeasureInSec = kpiIntervalMeasureInSec;
	}

}
