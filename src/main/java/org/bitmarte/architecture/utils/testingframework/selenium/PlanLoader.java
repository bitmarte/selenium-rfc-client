package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.InputField;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.evaluator.ContentEvaluatorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.ElementExtractorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.driver.DriverUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
import org.bitmarte.architecture.utils.testingframework.selenium.reports.ReportsUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class PlanLoader {

	private static final Logger LOG = LoggerFactory.getLogger(PlanLoader.class);

	public static void load(WebDriver driver, File xmlPlan) throws Exception {
		Run currentRun = null;
		E_TestResult testResult = null;
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Plan.class);
			xStream.processAnnotations(Run.class);
			xStream.processAnnotations(BrowserAction.class);
			xStream.processAnnotations(InputField.class);
			xStream.processAnnotations(SuccessCondition.class);
			xStream.processAnnotations(ErrorCondition.class);

			Plan plan = (Plan) xStream.fromXML(xmlPlan);

			String planFileName = StringUtils.substring(xmlPlan.getName(), 0,
					xmlPlan.getName().lastIndexOf("."));

			plan.setPlanName(planFileName);

			DriverUtils driverUtils = new DriverUtils(driver, planFileName);

			// cookies managing
			if (plan.isCookiesRemoveAll()) {
				driverUtils.removeAllCookies();
			}
			if (!StringUtils.isEmpty(plan.getCookiesRemove())) {
				driverUtils.removeCookies(plan.getCookiesRemove());
			}

			// window size managing
			if (plan.isFullscreen()) {
				driverUtils.fullScreen();
			}

			LOG.info(plan.getRuns().size() + " runs in plan '"
					+ xmlPlan.getName() + "'");

			// validation
			validatePlan(plan);

			for (Run run : plan.getRuns()) {
				currentRun = run;
				LOG.info("Run name: " + currentRun.getRunName());

				// cookies managing
				if (currentRun.isCookiesRemoveAll()) {
					driverUtils.removeAllCookies();
				}
				if (!StringUtils.isEmpty(plan.getCookiesRemove())) {
					driverUtils.removeCookies(plan.getCookiesRemove());
				}

				// window size managing
				if (currentRun.isFullscreen()) {
					driverUtils.fullScreen();
				} else {
					if (currentRun.getWindowWidthPx() > 0
							&& currentRun.getWindowHeightPx() > 0) {
						driverUtils.resizeWindow(currentRun.getWindowWidthPx(),
								currentRun.getWindowHeightPx());
					}
				}

				// browser action managing
				if (currentRun.getBrowserAction() != null) {
					driverUtils
							.makeBrowserAction(currentRun.getBrowserAction());
				}

				if (currentRun.getUrl() != null) {
					driver.get(currentRun.getUrl());
					LOG.info("Go to URL '" + currentRun.getUrl() + "'");
				}

				if (currentRun.getInputFields() != null) {
					LOG.info("Form filling...");
					for (InputField field : currentRun.getInputFields()) {
						ElementExtractorFactory
								.getInstance(field.getElementExtractor())
								.getElements(driver, field.getElement()).get(0)
								.sendKeys(field.getValue());
					}
				}

				if (currentRun.getClickByXPATH() != null) {
					LOG.info("Make a click on '" + currentRun.getClickByXPATH()
							+ "'");
					driver.findElement(By.xpath(currentRun.getClickByXPATH()))
							.click();
				}

				LOG.info("Test result checking...");
				WebDriverWait wait = new WebDriverWait(driver,
						DefaultSeleniumConfig.getConfig()
								.getMaxTimeOutPerPageInSec());
				try {
					LOG.debug("Serching by xpath '"
							+ currentRun.getSuccessCondition().getElement()
							+ "'  with content '"
							+ currentRun.getSuccessCondition()
									.getElementContent()
							+ "' unit "
							+ DefaultSeleniumConfig.getConfig()
									.getMaxTimeOutPerPageInSec() + " sec...");

					final Run finalRun = currentRun;
					wait.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							List<WebElement> elements = ElementExtractorFactory
									.getInstance(
											finalRun.getSuccessCondition()
													.getElementExtractor())
									.getElements(
											d,
											finalRun.getSuccessCondition()
													.getElement());
							for (WebElement webElement : elements) {
								return ContentEvaluatorFactory.getInstance(
										finalRun.getSuccessCondition()
												.getContentEvaluator())
										.evaluate(
												finalRun.getSuccessCondition()
														.getElementContent(),
												webElement.getText());
							}
							return false;
						}
					});

					run.getRunReport().setTestResult(E_TestResult.SUCCESS);
					testResult = driverUtils.takeScreenshot(run.getRunName(),
							E_TestResult.SUCCESS);
					LOG.info("Success on run '" + currentRun.getRunName() + "'");
				} catch (TimeoutException te1) {
					if (DefaultSeleniumConfig.getConfig().getErrorConditions() != null) {
						for (final ErrorCondition errorCondition : DefaultSeleniumConfig
								.getConfig().getErrorConditions()) {
							try {
								wait.until(new ExpectedCondition<Boolean>() {
									public Boolean apply(WebDriver d) {
										List<WebElement> elements = ElementExtractorFactory
												.getInstance(
														errorCondition
																.getElementExtractor())
												.getElements(
														d,
														errorCondition
																.getElement());
										if (errorCondition.getElementContent() != null) {
											for (WebElement webElement : elements) {
												return ContentEvaluatorFactory
														.getInstance(
																errorCondition
																		.getContentEvaluator())
														.evaluate(
																errorCondition
																		.getElementContent(),
																webElement
																		.getText());
											}
										}
										return false;
									}
								});

								plan.getPlanReport().setTestResult(
										E_TestResult.ERROR);
								run.getRunReport().setTestResult(
										E_TestResult.ERROR);
								testResult = driverUtils.takeScreenshot(
										run.getRunName(), E_TestResult.ERROR);
								LOG.error("Error on run '" + run.getRunName()
										+ "'");
								break;
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}

					plan.getPlanReport().setTestResult(E_TestResult.ERROR);
					run.getRunReport().setTestResult(E_TestResult.TIMEOUT);
					testResult = driverUtils.takeScreenshot(run.getRunName(),
							E_TestResult.TIMEOUT);
					LOG.error("Timeout on run '" + run.getRunName() + "'");
					break;
				}
			}

			switch (testResult) {
			case SUCCESS:
				LOG.info("Plan '" + xmlPlan.getName() + "' completed!");
				break;
			default:
				LOG.error("Plan '" + xmlPlan.getName()
						+ "' terminated with some error!");
				break;
			}

			// generating reports...
			ReportsUtils.generate(plan);

		} catch (Exception e) {
			LOG.error("Error load()!", e);
			throw e;
		}
	}

	private static void validatePlan(Plan plan) throws Exception {
		LOG.debug("Validating plan...");
		for (Run run : plan.getRuns()) {
			if (run.getRunName() == null) {
				throw new ConfigException(
						"No runName has been specified for current run!");
			}
			if (run.getSuccessCondition() == null) {
				throw new ConfigException(
						"No successCondition has been specified for run '"
								+ run.getRunName() + "'!");
			}
			if (plan.isFullscreen()) {
				if (run.getWindowHeightPx() > 0 || run.getWindowHeightPx() > 0) {
					throw new ConfigException(
							"Fullscreen setup in your plan, no custom window size allowed for run '"
									+ run.getRunName() + "'!");
				}
			}
			if (run.getBrowserAction() != null) {
				if (run.getUrl() != null) {
					throw new ConfigException(
							"Browser action setted, no url tag is allowed for run '"
									+ run.getRunName() + "'!");
				}
				if (run.getInputFields() != null) {
					throw new ConfigException(
							"Browser action setted, no input filling tag is allowed for run '"
									+ run.getRunName() + "'!");
				}
			}
		}
	}

}
