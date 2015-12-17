package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.InputField;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.evaluator.ContentEvaluatorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.ElementExtractorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
			xStream.processAnnotations(InputField.class);
			xStream.processAnnotations(SuccessCondition.class);

			Plan plan = (Plan) xStream.fromXML(xmlPlan);
			LOG.info(plan.getRuns().size() + " runs in plan '"
					+ xmlPlan.getName() + "'");

			for (Run run : plan.getRuns()) {
				validateRun(run);
				currentRun = run;
				LOG.info("Run name: " + currentRun.getRunName());

				if (currentRun.getWindowWidthPx() > 0
						&& currentRun.getWindowHeightPx() > 0) {
					LOG.info("Setting custom window size: "
							+ currentRun.getWindowWidthPx() + "px x "
							+ currentRun.getWindowHeightPx() + "px");
					driver.manage().window().setPosition(new Point(0, 0));
					Dimension d = new Dimension(currentRun.getWindowWidthPx(),
							currentRun.getWindowHeightPx());
					driver.manage().window().setSize(d);
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

					takeScreenshot(driver, currentRun, E_TestResult.SUCCESS);
					testResult = E_TestResult.SUCCESS;
					LOG.info("Success on run '" + currentRun.getRunName() + "'");
				} catch (TimeoutException te1) {
					try {
						wait.until(ExpectedConditions
								.presenceOfAllElementsLocatedBy(By
										.className("alert-danger")));
						takeScreenshot(driver, run, E_TestResult.ERROR);
						testResult = E_TestResult.ERROR;
						LOG.error("Error on run '" + run.getRunName() + "'");
						break;
					} catch (TimeoutException te2) {
						try {
							wait.until(ExpectedConditions
									.textToBePresentInElementLocated(
											By.className("wpsPortletBody"),
											"Si è verificato un errore."));
							takeScreenshot(driver, run, E_TestResult.ERROR);
							testResult = E_TestResult.ERROR;
							LOG.error("Error on run '" + run.getRunName() + "'");
							break;
						} catch (TimeoutException te3) {
							takeScreenshot(driver, run, E_TestResult.TIMEOUT);
							testResult = E_TestResult.TIMEOUT;
							LOG.error("Timeout on run '" + run.getRunName()
									+ "'");
							break;
						}
					}
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
		} catch (Exception e) {
			LOG.error("Error load()!", e);
			throw e;
		}
	}

	private static void takeScreenshot(WebDriver driver, Run run,
			E_TestResult testResult) {
		try {
			LOG.debug("Take screenshot '"
					+ DefaultSeleniumConfig.getConfig().getScreenshotBaseDir()
					+ run.getRunName() + "_" + testResult.toString() + ".png'");

			WebDriver augmentedDriver = driver;
			if (DefaultSeleniumConfig.getConfig().getSeleniumRcURL() != null) {
				augmentedDriver = new Augmenter().augment(driver);
			}
			File scrFile = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(DefaultSeleniumConfig
					.getConfig().getScreenshotBaseDir()
					+ run.getRunName()
					+ "_" + testResult.toString() + ".png"));
		} catch (Exception e) {
			LOG.error("Error takeScreenshot()!", e);
		}
	}

	private static void validateRun(Run run) throws Exception {
		if (run.getRunName() == null) {
			throw new ConfigException(
					"No runName has been specified for current run!");
		}
		if (run.getSuccessCondition() == null) {
			throw new ConfigException(
					"No successCondition has been specified for run '"
							+ run.getRunName() + "'!");
		}
	}
}
