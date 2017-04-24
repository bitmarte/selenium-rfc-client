package org.bitmarte.architecture.utils.testingframework.selenium.beans.reports;

/**
 * This class contains the pojo for W3C timings and the kpis, string constants
 * 
 * @author bitmarte
 */
public class WebTimingsReport {

	public static final String TOTAL_REQUESTS = "return window.performance.getEntries().length;";
	public static final String NAVIGATION_START = "return window.performance.timing.navigationStart;";
	public static final String START_TIME = "return window.performance.timing.startTime;";
	public static final String RESPONSE_START = "return window.performance.timing.responseStart;";
	public static final String REQUEST_START = "return window.performance.timing.requestStart;";
	public static final String RESPONSE_END = "return window.performance.timing.responseEnd;";
	public static final String DOMCONTENT_LOAD_EVENT_START = "return window.performance.timing.domContentLoadedEventStart;";
	public static final String DOMCONTENT_LOAD_EVENT_END = "return window.performance.timing.domContentLoadedEventEnd;";
	public static final String FETCH_START = "return window.performance.timing.fetchStart;";
	public static final String LOAD_EVENT_START = "return window.performance.timing.loadEventStart;";
	public static final String LOAD_EVENT_END = "return window.performance.timing.loadEventEnd;";
	public static final String REDIRECT_START = "return window.performance.timing.redirectStart;";
	public static final String REDIRECT_END = "return window.performance.timing.redirectEnd;";
	public static final String DOMAIN_LOOKUP_START = "return window.performance.timing.domainLookupStart;";
	public static final String DOMAIN_LOOKUP_END = "return window.performance.timing.domainLookupEnd;";
	public static final String CONNECT_START = "return window.performance.timing.connectStart;";
	public static final String CONNECT_END = "return window.performance.timing.connectEnd;";
	public static final String UNLOAD_EVENT_START = "return window.performance.timing.unloadEventStart;";
	public static final String UNLOAD_EVENT_END = "return window.performance.timing.unloadEventEnd;";
	public static final String DOMLOADING = "return window.performance.timing.domLoading;";
	public static final String DOMINTERACTIVE = "return window.performance.timing.domInteractive;";
	public static final String DOMCOMPLETE = "return window.performance.timing.domComplete;";

	private long totalRequests = -1;
	private long navigationStart = -1;
	private long responseStart = -1;
	private long requestStart = -1;
	private long responseEnd = -1;
	private long domContentLoadedEventStart = -1;
	private long domContentLoadedEventEnd = -1;
	private long fetchStart = -1;
	private long loadEventStart = -1;
	private long loadEventEnd = -1;
	private long redirectStart = -1;
	private long redirectEnd = -1;
	private long domainLookupStart = -1;
	private long domainLookupEnd = -1;
	private long connectStart = -1;
	private long connectEnd = -1;
	private long unloadEventStart = -1;
	private long unloadEventEnd = -1;
	private long domLoading = -1;
	private long domInteractive = -1;
	private long domComplete = -1;

	public long getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(long totalRequests) {
		this.totalRequests = totalRequests;
	}

	public long getNavigationStart() {
		return navigationStart;
	}

	public void setNavigationStart(long navigationStart) {
		this.navigationStart = navigationStart;
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

	public long getDomContentLoadedEventStart() {
		return domContentLoadedEventStart;
	}

	public void setDomContentLoadedEventStart(long domContentLoadedEventStart) {
		this.domContentLoadedEventStart = domContentLoadedEventStart;
	}

	public long getDomContentLoadedEventEnd() {
		return domContentLoadedEventEnd;
	}

	public void setDomContentLoadedEventEnd(long domContentLoadedEventEnd) {
		this.domContentLoadedEventEnd = domContentLoadedEventEnd;
	}

	public long getFetchStart() {
		return fetchStart;
	}

	public void setFetchStart(long fetchStart) {
		this.fetchStart = fetchStart;
	}

	public long getLoadEventStart() {
		return loadEventStart;
	}

	public void setLoadEventStart(long loadEventStart) {
		this.loadEventStart = loadEventStart;
	}

	public long getLoadEventEnd() {
		return loadEventEnd;
	}

	public void setLoadEventEnd(long loadEventEnd) {
		this.loadEventEnd = loadEventEnd;
	}

	public long getRedirectStart() {
		return redirectStart;
	}

	public void setRedirectStart(long redirectStart) {
		this.redirectStart = redirectStart;
	}

	public long getRedirectEnd() {
		return redirectEnd;
	}

	public void setRedirectEnd(long redirectEnd) {
		this.redirectEnd = redirectEnd;
	}

	public long getDomainLookupStart() {
		return domainLookupStart;
	}

	public void setDomainLookupStart(long domainLookupStart) {
		this.domainLookupStart = domainLookupStart;
	}

	public long getDomainLookupEnd() {
		return domainLookupEnd;
	}

	public void setDomainLookupEnd(long domainLookupEnd) {
		this.domainLookupEnd = domainLookupEnd;
	}

	public long getConnectStart() {
		return connectStart;
	}

	public void setConnectStart(long connectStart) {
		this.connectStart = connectStart;
	}

	public long getConnectEnd() {
		return connectEnd;
	}

	public void setConnectEnd(long connectEnd) {
		this.connectEnd = connectEnd;
	}

	public long getUnloadEventStart() {
		return unloadEventStart;
	}

	public void setUnloadEventStart(long unloadEventStart) {
		this.unloadEventStart = unloadEventStart;
	}

	public long getUnloadEventEnd() {
		return unloadEventEnd;
	}

	public void setUnloadEventEnd(long unloadEventEnd) {
		this.unloadEventEnd = unloadEventEnd;
	}

	public long getDomLoading() {
		return domLoading;
	}

	public void setDomLoading(long domLoading) {
		this.domLoading = domLoading;
	}

	public long getDomInteractive() {
		return domInteractive;
	}

	public void setDomInteractive(long domInteractive) {
		this.domInteractive = domInteractive;
	}

	public long getDomComplete() {
		return domComplete;
	}

	public void setDomComplete(long domComplete) {
		this.domComplete = domComplete;
	}

}
