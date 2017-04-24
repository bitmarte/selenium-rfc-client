package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This class configure the timeouts (delay) for taking W3C timings
 * 
 * @author bitmarte
 */
@XStreamAlias("webTimings")
public class WebTimingsConfig {

	/**
	 * Waiting time before take the timinings after succes or error condition
	 */
	@XStreamAlias("maxTimeoutPerMeasureInSec")
	private int maxTimeoutPerMeasureInSec;

	/**
	 * In order to take a right measurements the frameworks makes more than a
	 * single measurement, until the result will be different from the previous
	 * one. This is the time between two different measurements
	 */
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
