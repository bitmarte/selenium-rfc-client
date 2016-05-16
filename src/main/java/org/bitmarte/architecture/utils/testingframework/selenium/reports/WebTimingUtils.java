package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.WebTimings;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class WebTimingUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(WebTimingUtils.class);

	private WebDriver driver = null;

	public WebTimingUtils(WebDriver driver) {
		this.driver = driver;
	}

	private long ritrieveTime(String kpi) {
		final String myKpi = kpi;
		final JavascriptExecutor js = (JavascriptExecutor) this.driver;
		WebDriverWait wait = new WebDriverWait(driver,
				DefaultSeleniumConfig.getConfig().getWebTimings().getMaxTimeoutPerMeasureInSec());
		wait.until(new ExpectedCondition<Boolean>() {
			long value = -1;

			public Boolean apply(WebDriver d) {
				if (DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() > 0) {
					try {
						Thread.currentThread();
						Thread.sleep(
								DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() * 1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				long valueTmp = (Long) js.executeScript(myKpi);
				if (valueTmp != value) {
					value = valueTmp;
					return false;
				} else {
					return true;
				}
			}
		});
		return (Long) js.executeScript(myKpi);
	}

	public void calculateTimings(Run run) throws Exception {
		LOG.debug("monitor performance with webTimingsAPI...");
		if (run.getRunReport().getWebTimings() == null) {
			run.getRunReport().setWebTimings(new WebTimings());
		}
		if (DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() > 0) {
			LOG.debug("using KpiIntervalMeasureInSec '"
					+ DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() + "s'...");
		}

		// Total request
		long totalRequest = this.ritrieveTime(WebTimings.TOTAL_REQUESTS);
		run.getRunReport().getWebTimings().setTotalRequests(totalRequest);
		LOG.debug("Total request: " + totalRequest);

		// Request start
		long requestStart = this.ritrieveTime(WebTimings.REQUEST_START);
		run.getRunReport().getWebTimings().setRequestStart(requestStart);
		LOG.debug("Request start: " + requestStart);

		// Fetch start
		long fetchStart = this.ritrieveTime(WebTimings.FETCH_START);
		run.getRunReport().getWebTimings().setFetchStart(fetchStart);
		LOG.debug("Fetch start: " + fetchStart);

		// Navigation start
		long navigationStart = this.ritrieveTime(WebTimings.NAVIGATION_START);
		run.getRunReport().getWebTimings().setNavigationStart(navigationStart);
		LOG.debug("Navigation start: " + navigationStart);

		// Reponse start
		long responseStart = this.ritrieveTime(WebTimings.RESPONSE_START);
		run.getRunReport().getWebTimings().setResponseStart(responseStart);
		LOG.debug("Response start: " + responseStart);

		// Response end
		long responseEnd = this.ritrieveTime(WebTimings.RESPONSE_END);
		run.getRunReport().getWebTimings().setResponseEnd(responseEnd);
		LOG.debug("Response end: " + responseEnd);

		// Dom content load event start
		long domContentLoadedEventStart = this.ritrieveTime(WebTimings.DOMCONTENT_LOAD_EVENT_START);
		run.getRunReport().getWebTimings().setDomContentLoadedEventStart(domContentLoadedEventStart);
		LOG.debug("DomContent loaded event start: " + domContentLoadedEventStart);

		// Dom content load event end
		long domContentLoadedEventEnd = this.ritrieveTime(WebTimings.DOMCONTENT_LOAD_EVENT_END);
		run.getRunReport().getWebTimings().setDomContentLoadedEventEnd(domContentLoadedEventEnd);
		LOG.debug("DomContent loaded event end: " + domContentLoadedEventEnd);

		// Load event start
		long loadEventStart = this.ritrieveTime(WebTimings.LOAD_EVENT_START);
		run.getRunReport().getWebTimings().setLoadEventStart(loadEventStart);
		LOG.debug("Load event start: " + loadEventStart);

		// Load event end
		long loadEventEnd = this.ritrieveTime(WebTimings.LOAD_EVENT_END);
		run.getRunReport().getWebTimings().setLoadEventEnd(loadEventEnd);
		LOG.debug("Load event end: " + loadEventEnd);

		// Redirect start
		long redirectStart = this.ritrieveTime(WebTimings.REDIRECT_START);
		run.getRunReport().getWebTimings().setRedirectStart(redirectStart);
		LOG.debug("Redirect start: " + redirectStart);

		// Redirect end
		long redirectEnd = this.ritrieveTime(WebTimings.REDIRECT_END);
		run.getRunReport().getWebTimings().setRedirectEnd(redirectEnd);
		LOG.debug("Redirect end: " + redirectEnd);

		// Domain lookup start
		long domainLookupStart = this.ritrieveTime(WebTimings.DOMAIN_LOOKUP_START);
		run.getRunReport().getWebTimings().setDomainLookupStart(domainLookupStart);
		LOG.debug("Domain lookup start: " + domainLookupStart);

		// Domain lookup end
		long domainLookupEnd = this.ritrieveTime(WebTimings.DOMAIN_LOOKUP_END);
		run.getRunReport().getWebTimings().setDomainLookupEnd(domainLookupEnd);
		LOG.debug("Domain lookup end: " + redirectEnd);

		// Connect start
		long connectStart = this.ritrieveTime(WebTimings.CONNECT_START);
		run.getRunReport().getWebTimings().setConnectStart(connectStart);
		LOG.debug("Connect start: " + connectStart);

		// Connect end
		long connectEnd = this.ritrieveTime(WebTimings.CONNECT_END);
		run.getRunReport().getWebTimings().setConnectEnd(connectEnd);
		LOG.debug("Connect end: " + connectEnd);

		// Unload event start
		long unloadEventStart = this.ritrieveTime(WebTimings.UNLOAD_EVENT_START);
		run.getRunReport().getWebTimings().setUnloadEventStart(unloadEventStart);
		LOG.debug("Unload event start: " + unloadEventStart);

		// Unload event end
		long unloadEventEnd = this.ritrieveTime(WebTimings.UNLOAD_EVENT_END);
		run.getRunReport().getWebTimings().setUnloadEventEnd(unloadEventEnd);
		LOG.debug("Unload event end: " + unloadEventEnd);

		// DomLoading
		long domLoading = this.ritrieveTime(WebTimings.DOMLOADING);
		run.getRunReport().getWebTimings().setDomLoading(domLoading);
		LOG.debug("DomLoading: " + domLoading);

		// DomInteractive
		long domInteractive = this.ritrieveTime(WebTimings.DOMINTERACTIVE);
		run.getRunReport().getWebTimings().setDomInteractive(domInteractive);
		LOG.debug("DomInteractive: " + domInteractive);

		// DomComplete
		long domComplete = this.ritrieveTime(WebTimings.DOMCOMPLETE);
		run.getRunReport().getWebTimings().setDomComplete(domComplete);
		LOG.debug("DomComplete: " + domComplete);
	}
}
