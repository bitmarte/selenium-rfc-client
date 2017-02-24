package org.bitmarte.architecture.utils.testingframework.selenium.utils;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.WebTimingsReport;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class WebTimingUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(WebTimingUtils.class);

	private static final long NO_VALUE = -1;

	private WebDriver driver = null;

	public WebTimingUtils(WebDriver driver) {
		this.driver = driver;
	}

	private long ritrieveTime(String kpi, long previousValue) {
		final String myKpi = kpi;
		final JavascriptExecutor js = (JavascriptExecutor) this.driver;
		if (SeleniumConfigProvider.getConfig().getWebTimings().getKpiIntervalMeasureInSec() > 0) {
			try {
				Thread.currentThread();
				Thread.sleep(SeleniumConfigProvider.getConfig().getWebTimings().getKpiIntervalMeasureInSec() * 1000);
			} catch (Exception e) {
				LOG.warn("Unexpected exception on thread sleep!", e);
			}
		}
		long valueTmp = (Long) js.executeScript(myKpi);
		if (valueTmp != previousValue) {
			ritrieveTime(myKpi, valueTmp);
		}
		return valueTmp;
	}

	public void calculateTimings(Run run) throws Exception {
		LOG.debug("monitor performance with webTimingsAPI...");
		if (run.getRunReport().getWebTimings() == null) {
			run.getRunReport().setWebTimings(new WebTimingsReport());
		}
		if (SeleniumConfigProvider.getConfig().getWebTimings().getKpiIntervalMeasureInSec() > 0) {
			LOG.debug("using KpiIntervalMeasureInSec '"
					+ SeleniumConfigProvider.getConfig().getWebTimings().getKpiIntervalMeasureInSec() + "s'...");
		}

		// Total request
		long totalRequest = this.ritrieveTime(WebTimingsReport.TOTAL_REQUESTS, NO_VALUE);
		run.getRunReport().getWebTimings().setTotalRequests(totalRequest);
		LOG.debug("Total request: " + totalRequest);

		// Request start
		long requestStart = this.ritrieveTime(WebTimingsReport.REQUEST_START, NO_VALUE);
		run.getRunReport().getWebTimings().setRequestStart(requestStart);
		LOG.debug("Request start: " + requestStart);

		// Fetch start
		long fetchStart = this.ritrieveTime(WebTimingsReport.FETCH_START, NO_VALUE);
		run.getRunReport().getWebTimings().setFetchStart(fetchStart);
		LOG.debug("Fetch start: " + fetchStart);

		// Navigation start
		long navigationStart = this.ritrieveTime(WebTimingsReport.NAVIGATION_START, NO_VALUE);
		run.getRunReport().getWebTimings().setNavigationStart(navigationStart);
		LOG.debug("Navigation start: " + navigationStart);

		// Reponse start
		long responseStart = this.ritrieveTime(WebTimingsReport.RESPONSE_START, NO_VALUE);
		run.getRunReport().getWebTimings().setResponseStart(responseStart);
		LOG.debug("Response start: " + responseStart);

		// Response end
		long responseEnd = this.ritrieveTime(WebTimingsReport.RESPONSE_END, NO_VALUE);
		run.getRunReport().getWebTimings().setResponseEnd(responseEnd);
		LOG.debug("Response end: " + responseEnd);

		// Dom content load event start
		long domContentLoadedEventStart = this.ritrieveTime(WebTimingsReport.DOMCONTENT_LOAD_EVENT_START, NO_VALUE);
		run.getRunReport().getWebTimings().setDomContentLoadedEventStart(domContentLoadedEventStart);
		LOG.debug("DomContent loaded event start: " + domContentLoadedEventStart);

		// Dom content load event end
		long domContentLoadedEventEnd = this.ritrieveTime(WebTimingsReport.DOMCONTENT_LOAD_EVENT_END, NO_VALUE);
		run.getRunReport().getWebTimings().setDomContentLoadedEventEnd(domContentLoadedEventEnd);
		LOG.debug("DomContent loaded event end: " + domContentLoadedEventEnd);

		// Load event start
		long loadEventStart = this.ritrieveTime(WebTimingsReport.LOAD_EVENT_START, NO_VALUE);
		run.getRunReport().getWebTimings().setLoadEventStart(loadEventStart);
		LOG.debug("Load event start: " + loadEventStart);

		// Load event end
		long loadEventEnd = this.ritrieveTime(WebTimingsReport.LOAD_EVENT_END, NO_VALUE);
		run.getRunReport().getWebTimings().setLoadEventEnd(loadEventEnd);
		LOG.debug("Load event end: " + loadEventEnd);

		// Redirect start
		long redirectStart = this.ritrieveTime(WebTimingsReport.REDIRECT_START, NO_VALUE);
		run.getRunReport().getWebTimings().setRedirectStart(redirectStart);
		LOG.debug("Redirect start: " + redirectStart);

		// Redirect end
		long redirectEnd = this.ritrieveTime(WebTimingsReport.REDIRECT_END, NO_VALUE);
		run.getRunReport().getWebTimings().setRedirectEnd(redirectEnd);
		LOG.debug("Redirect end: " + redirectEnd);

		// Domain lookup start
		long domainLookupStart = this.ritrieveTime(WebTimingsReport.DOMAIN_LOOKUP_START, NO_VALUE);
		run.getRunReport().getWebTimings().setDomainLookupStart(domainLookupStart);
		LOG.debug("Domain lookup start: " + domainLookupStart);

		// Domain lookup end
		long domainLookupEnd = this.ritrieveTime(WebTimingsReport.DOMAIN_LOOKUP_END, NO_VALUE);
		run.getRunReport().getWebTimings().setDomainLookupEnd(domainLookupEnd);
		LOG.debug("Domain lookup end: " + redirectEnd);

		// Connect start
		long connectStart = this.ritrieveTime(WebTimingsReport.CONNECT_START, NO_VALUE);
		run.getRunReport().getWebTimings().setConnectStart(connectStart);
		LOG.debug("Connect start: " + connectStart);

		// Connect end
		long connectEnd = this.ritrieveTime(WebTimingsReport.CONNECT_END, NO_VALUE);
		run.getRunReport().getWebTimings().setConnectEnd(connectEnd);
		LOG.debug("Connect end: " + connectEnd);

		// Unload event start
		long unloadEventStart = this.ritrieveTime(WebTimingsReport.UNLOAD_EVENT_START, NO_VALUE);
		run.getRunReport().getWebTimings().setUnloadEventStart(unloadEventStart);
		LOG.debug("Unload event start: " + unloadEventStart);

		// Unload event end
		long unloadEventEnd = this.ritrieveTime(WebTimingsReport.UNLOAD_EVENT_END, NO_VALUE);
		run.getRunReport().getWebTimings().setUnloadEventEnd(unloadEventEnd);
		LOG.debug("Unload event end: " + unloadEventEnd);

		// DomLoading
		long domLoading = this.ritrieveTime(WebTimingsReport.DOMLOADING, NO_VALUE);
		run.getRunReport().getWebTimings().setDomLoading(domLoading);
		LOG.debug("DomLoading: " + domLoading);

		// DomInteractive
		long domInteractive = this.ritrieveTime(WebTimingsReport.DOMINTERACTIVE, NO_VALUE);
		run.getRunReport().getWebTimings().setDomInteractive(domInteractive);
		LOG.debug("DomInteractive: " + domInteractive);

		// DomComplete
		long domComplete = this.ritrieveTime(WebTimingsReport.DOMCOMPLETE, NO_VALUE);
		run.getRunReport().getWebTimings().setDomComplete(domComplete);
		LOG.debug("DomComplete: " + domComplete);
	}
}
