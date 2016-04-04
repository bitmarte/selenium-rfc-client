package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.WebTimings;
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
					LOG.debug("using KpiIntervalMeasureInSec '"
							+ DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() + "s'...");
					try {
						Thread.currentThread();
						Thread.sleep(
								DefaultSeleniumConfig.getConfig().getWebTimings().getKpiIntervalMeasureInSec() * 1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				long valueTmp = (Long) js.executeScript(myKpi);
				LOG.debug("" + valueTmp);
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
		run.getRunReport().getWebTimings().setFetctStart(fetchStart);
		LOG.debug("Fetch start: " + fetchStart);

		// Reponse start
		long responseStart = this.ritrieveTime(WebTimings.RESPONSE_START);
		run.getRunReport().getWebTimings().setResponseStart(responseStart);
		LOG.debug("Response start: " + responseStart);

		// Response end
		long responseEnd = this.ritrieveTime(WebTimings.RESPONSE_END);
		run.getRunReport().getWebTimings().setResponseEnd(responseEnd);
		LOG.debug("Response end: " + responseEnd);

		// Dom content load event end
		long domContentLoadEventEnd = this.ritrieveTime(WebTimings.DOMCONTENT_LOAD_EVENT_END);
		run.getRunReport().getWebTimings().setDomContentLoadEventEnd(domContentLoadEventEnd);
		LOG.debug("DomContent load event end: " + domContentLoadEventEnd);
	}
}
