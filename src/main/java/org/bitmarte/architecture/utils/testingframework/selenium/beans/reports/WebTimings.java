package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

/**
 * @author bitmarte
 *
 */
public class WebTimings {

	public static final String TOTAL_REQUESTS = "return window.performance.getEntries().length;";
	public static final String RESPONSE_START = "return window.performance.timing.responseStart;";
	public static final String REQUEST_START = "return window.performance.timing.requestStart;";
	public static final String RESPONSE_END = "return window.performance.timing.responseEnd;";
	public static final String DOMCONTENT_LOAD_EVENT_END = "return window.performance.timing.domContentLoadedEventEnd;";
	public static final String FETCH_START = "return window.performance.timing.fetchStart;";

	private long totalRequests = -1;
	private long responseStart = -1;
	private long requestStart = -1;
	private long responseEnd = -1;
	private long domContentLoadEventEnd = -1;
	private long fetctStart = -1;

	public long getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(long totalRequests) {
		this.totalRequests = totalRequests;
	}

	public long getResponseStart() {
		return responseStart;
	}

	public void setResponseStart(long responseStart) {
		this.responseStart = responseStart;
	}

	public long getRequestStart() {
		return requestStart;
	}

	public void setRequestStart(long requestStart) {
		this.requestStart = requestStart;
	}

	public long getResponseEnd() {
		return responseEnd;
	}

	public void setResponseEnd(long responseEnd) {
		this.responseEnd = responseEnd;
	}

	public long getDomContentLoadEventEnd() {
		return domContentLoadEventEnd;
	}

	public void setDomContentLoadEventEnd(long domContentLoadEventEnd) {
		this.domContentLoadEventEnd = domContentLoadEventEnd;
	}

	public long getFetctStart() {
		return fetctStart;
	}

	public void setFetctStart(long fetctStart) {
		this.fetctStart = fetctStart;
	}

}
