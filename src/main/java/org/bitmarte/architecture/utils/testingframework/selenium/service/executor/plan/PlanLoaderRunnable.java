package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.E_AuthType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.authentication.impl.NTLMAuthentication;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.ContentEvaluatorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.exceptions.ContentEvaluatorException;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action.BrowserActionExecutorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.ElementExtractorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.E_ReportType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.ReportProducerFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.utils.DriverUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.utils.WebTimingUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.proxy.CaptureType;

/**
 * This is the plan loader, runnable implementation for multi threads runs
 *
 * @author bitmarte
 */
public class PlanLoaderRunnable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(PlanLoaderRunnable.class);

	private WebDriver driver = null;
	private Plan plan = null;
	private BrowserMobProxy proxy = null;
	private WorkingPlans workingPlans = null;

	public PlanLoaderRunnable(WebDriver driver, Plan plan, BrowserMobProxy proxy, WorkingPlans workingPlans) {
		this.driver = driver;
		this.plan = plan;
		this.proxy = proxy;
		this.workingPlans = workingPlans;
	}

	/**
	 * The {@link Runnable} implementation for each plans. This method allows to
	 * execute more plan at the same time, each plans have a dedicated thread, for
	 * concurrent run
	 */
	public void run() {
		LOG.info(Thread.currentThread().getName() + " running for plan " + plan.getPlanName());

		Run currentRun = null;
		DriverUtils driverUtils = null;
		WebTimingUtils timingUtils = null;

		try {
			driverUtils = new DriverUtils(driver, plan.getPlanName());
			timingUtils = new WebTimingUtils(driver);

			LOG.info(plan.getRuns().size() + " runs in plan '" + plan.getPlanName() + "'");

			for (Run run : plan.getRuns()) {
				currentRun = run;
				currentRun.getRunReport().setTestResult(E_TestResult.ERROR.name());
				LOG.info("Run name: " + currentRun.getRunName());

				// enable HAR capture
				if (SeleniumConfigProvider.getConfig().getMobProxy() != null
						&& Boolean.valueOf(SeleniumConfigProvider.getConfig().getMobProxy().isEnableHarCapture())) {
					LOG.info("HAR file capture enabled");
					proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
					proxy.newHar(currentRun.getRunName());
					proxy.newPage(currentRun.getRunName());
				}

				// manage authentication
				if (currentRun.getAuthentication() != null) {
					switch (E_AuthType.valueOf(currentRun.getAuthentication().getAuthType())) {
					default:
						LOG.debug("using authType '" + E_AuthType.NTLM.name() + "'...");
						(new Thread(new NTLMAuthentication(currentRun))).start();
						Thread.currentThread();
						Thread.sleep(currentRun.getAuthentication().getWaitPromptInSec() + 8000);
						break;
					}
				}

				// manage browser actions
				if (currentRun.getBrowserActions() != null) {
					for (A_BrowserAction browserAction : currentRun.getBrowserActions()) {
						try {
							BrowserActionExecutorFactory.getInstance(driver, browserAction).execute();
						} catch (ElementNotInteractableException e) {
							LOG.warn("Element not visibile, skip it...");
						}
					}
				}

				// checking successCondition
				LOG.info("Test result checking on success conditions...");
				final Run finalRun = currentRun;
				try {
					WebElement el = ElementExtractorFactory
							.getInstance(finalRun.getSuccessCondition().getElementExtractor()).getElement(driver,
									finalRun.getSuccessCondition().getElement(), finalRun.getSuccessCondition());
					ContentEvaluatorFactory.getInstance(finalRun.getSuccessCondition().getContentEvaluator())
							.evaluate(finalRun.getSuccessCondition().getElementContent(), el.getText());

					currentRun.getRunReport().setTestResult(E_TestResult.SUCCESS.name());
					LOG.info("Success on run '" + currentRun.getRunName() + "'");
				} catch (TimeoutException | ContentEvaluatorException tce1) {
					LOG.warn(tce1.getMessage());
					LOG.info("Test result checking on error conditions...");
					List<ErrorCondition> errorConditions = SeleniumConfigProvider.getConfig().getErrorConditions();
					if (currentRun.getErrorConditions() != null) {
						errorConditions = currentRun.getErrorConditions();
						LOG.info("Using ErrorConditions specified in current run...");
					}

					for (final ErrorCondition errorCondition : errorConditions) {
						try {
							WebElement el = ElementExtractorFactory.getInstance(errorCondition.getElementExtractor())
									.getElement(driver, errorCondition.getElement(), errorCondition);
							ContentEvaluatorFactory.getInstance(errorCondition.getContentEvaluator())
									.evaluate(errorCondition.getElementContent(), el.getText());
							LOG.error("Error on run '" + currentRun.getRunName() + "'");
							break;
						} catch (Exception e) {
							currentRun.getRunReport().setTestResult(E_TestResult.TIMEOUT.name());
							LOG.error("Timeout on run '" + currentRun.getRunName() + "'");
						}
					}

					break;
				} finally {
					driverUtils.takeScreenshot(currentRun, null);

					// Web Timings API
					if (SeleniumConfigProvider.getConfig().getWebTimings() != null) {
						timingUtils.calculateTimings(currentRun);
					}

					// HAR capture
					if (SeleniumConfigProvider.getConfig().getMobProxy() != null
							&& Boolean.valueOf(SeleniumConfigProvider.getConfig().getMobProxy().isEnableHarCapture())) {
						String harFilePath = SeleniumConfigProvider.getConfig().getReportBaseDir() + plan.getPlanName()
								+ "/HarFiles/" + currentRun.getRunName() + ".har";
						LOG.info("writing HAR file '" + harFilePath + "' ...");
						File harFile = new File(harFilePath);
						harFile.getParentFile().mkdirs();
						proxy.getHar().writeTo(harFile);
						currentRun.getRunReport().setHarFilePath(harFilePath);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error run()!", e);

			try {
				driverUtils.takeScreenshot(currentRun, E_TestResult.ERROR);
			} catch (Exception e1) {
				LOG.error("Error run()!", e1);
			}
		} finally {
			switch (E_TestResult.valueOf(plan.getPlanResult())) {
			case SUCCESS:
				LOG.info("Plan '" + plan.getPlanName() + "' completed!");
				break;
			default:
				LOG.error("Plan '" + plan.getPlanName() + "' terminated with some error!");
				break;
			}
			// generating reports...
			try {
				List<Plan> plans = new ArrayList<Plan>();
				plans.add(plan);
				ReportProducerFactory.getInstance(E_ReportType.HTML_PLAN, plans).produce();

				this.workingPlans.regWorkedPlan(plan);

				if (Boolean.valueOf(SeleniumConfigProvider.getConfig().isCloseBrowserOnFinish())) {
					try {
						this.driver.quit();
					} catch (Throwable t) {
						LOG.error("Driver does not close correctly!", t);
					}
				}

			} catch (Exception e) {
				LOG.error("Error run()!", e);
			}
		}
	}

}
