package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.authentication.NTLMAuthentication;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Authentication;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.InputField;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_AuthType;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.evaluator.ContentEvaluatorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.ElementExtractorFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.driver.DriverUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.exceptions.ConfigException;
import org.bitmarte.architecture.utils.testingframework.selenium.reports.ReportProducer;
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

	@SuppressWarnings("finally")
	public static Plan load(WebDriver driver, File xmlPlan) throws Exception {
		Plan plan = null;
		Run currentRun = null;
		DriverUtils driverUtils = null;
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Plan.class);
			xStream.processAnnotations(Authentication.class);
			xStream.processAnnotations(Run.class);
			xStream.processAnnotations(BrowserAction.class);
			xStream.processAnnotations(InputField.class);
			xStream.processAnnotations(SuccessCondition.class);
			xStream.processAnnotations(ErrorCondition.class);

			plan = (Plan) xStream.fromXML(xmlPlan);

			String planFileName = StringUtils.substring(xmlPlan.getName(), 0, xmlPlan.getName().lastIndexOf("."));

			plan.setPlanName(planFileName);

			plan.getPlanReport().setTestResult(E_TestResult.ERROR.name());
		} catch (Exception e) {
			throw e;
		}

		try {
			driverUtils = new DriverUtils(driver, plan.getPlanName());

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

			LOG.info(plan.getRuns().size() + " runs in plan '" + xmlPlan.getName() + "'");

			// validation
			validatePlan(plan);

			for (Run run : plan.getRuns()) {
				currentRun = run;
				currentRun.getRunReport().setTestResult(E_TestResult.ERROR.name());
				LOG.info("Run name: " + currentRun.getRunName());

				// browser action managing
				if (currentRun.getBrowserAction() != null) {
					driverUtils.makeBrowserAction(currentRun.getBrowserAction());
				}

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
					if (currentRun.getWindowWidthPx() > 0 && currentRun.getWindowHeightPx() > 0) {
						driverUtils.resizeWindow(currentRun.getWindowWidthPx(), currentRun.getWindowHeightPx());
					}
				}

				WebDriverWait wait = null;

				if (currentRun.getUrl() != null) {
					LOG.info("Go to URL '" + currentRun.getUrl() + "'");
					driver.get(currentRun.getUrl());
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

				if (currentRun.getInputFields() != null) {
					LOG.info("Form filling...");
					for (InputField field : currentRun.getInputFields()) {
						ElementExtractorFactory.getInstance(field.getElementExtractor())
								.getElements(driver, field.getElement()).get(0).sendKeys(field.getValue());
					}
				}

				if (currentRun.getClickByXPATH() != null) {
					LOG.info("Make a click on '" + currentRun.getClickByXPATH() + "'");
					driver.findElement(By.xpath(currentRun.getClickByXPATH())).click();
				}

				LOG.info("Test result checking...");
				final Run finalRun = currentRun;
				try {
					wait = new WebDriverWait(driver,
							DefaultSeleniumConfig.getConfig().getMaxTimeOutPerSuccessConditionInSec());
					LOG.debug("Serching success condition unit "
							+ DefaultSeleniumConfig.getConfig().getMaxTimeOutPerSuccessConditionInSec() + " sec...");

					wait.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							List<WebElement> elements = ElementExtractorFactory
									.getInstance(finalRun.getSuccessCondition().getElementExtractor())
									.getElements(d, finalRun.getSuccessCondition().getElement());
							if (!elements.isEmpty()) {
								if (finalRun.getSuccessCondition().getElementContent() != null) {
									for (WebElement webElement : elements) {
										return ContentEvaluatorFactory
												.getInstance(finalRun.getSuccessCondition().getContentEvaluator())
												.evaluate(finalRun.getSuccessCondition().getElementContent(),
														webElement.getText());
									}
								} else {
									return true;
								}
							}
							return false;
						}
					});

					plan.getPlanReport().setTestResult(E_TestResult.SUCCESS.name());
					currentRun.getRunReport().setTestResult(E_TestResult.SUCCESS.name());
					LOG.info("Success on run '" + currentRun.getRunName() + "'");
				} catch (TimeoutException te1) {
					wait = new WebDriverWait(driver,
							DefaultSeleniumConfig.getConfig().getMaxTimeOutPerErrorConditionInSec());
					LOG.debug("Searching error condition unit "
							+ DefaultSeleniumConfig.getConfig().getMaxTimeOutPerErrorConditionInSec() + " sec...");
					if (DefaultSeleniumConfig.getConfig().getErrorConditions() != null) {
						for (final ErrorCondition errorCondition : DefaultSeleniumConfig.getConfig()
								.getErrorConditions()) {
							try {
								wait.until(new ExpectedCondition<Boolean>() {
									public Boolean apply(WebDriver d) {
										List<WebElement> elements = ElementExtractorFactory
												.getInstance(errorCondition.getElementExtractor())
												.getElements(d, errorCondition.getElement());
										if (!elements.isEmpty()) {
											if (errorCondition.getElementContent() != null) {
												for (WebElement webElement : elements) {
													return ContentEvaluatorFactory
															.getInstance(errorCondition.getContentEvaluator())
															.evaluate(errorCondition.getElementContent(),
																	webElement.getText());
												}
											} else {
												return true;
											}
										}
										return false;
									}
								});
								LOG.error("Error on run '" + currentRun.getRunName() + "'");

								break;
							} catch (Exception e) {
								currentRun.getRunReport().setTestResult(E_TestResult.TIMEOUT.name());
								LOG.error("Timeout on run '" + currentRun.getRunName() + "'");
							}
						}
					} else {
						currentRun.getRunReport().setTestResult(E_TestResult.TIMEOUT.name());
						LOG.error("Timeout on run '" + currentRun.getRunName() + "'");
					}
					break;
				} finally {
					if (currentRun.getBrowserAction() != null
							&& E_BrowserAction.IFRAME_SWITCH.name().equals(currentRun.getBrowserAction().getAction())
							&& currentRun.getBrowserAction().getElementByXPath() == null) {
						LOG.debug("iframe used, switching to default content...");
						driver.switchTo().defaultContent();
					}
					driverUtils.takeScreenshot(currentRun.getRunName(),
							E_TestResult.valueOf(currentRun.getRunReport().getTestResult()));
				}
			}

			switch (E_TestResult.valueOf(plan.getPlanReport().getTestResult())) {
			case SUCCESS:
				LOG.info("Plan '" + xmlPlan.getName() + "' completed!");
				break;
			default:
				LOG.error("Plan '" + xmlPlan.getName() + "' terminated with some error!");
				break;
			}

		} catch (Exception e) {
			LOG.error("Error load()!", e);

			driverUtils.takeScreenshot(currentRun.getRunName(), E_TestResult.ERROR);
			throw e;
		} finally {
			// generating reports...
			ReportProducer.generatePlanReport(plan);
			return plan;
		}
	}

	private static void validatePlan(Plan plan) throws Exception {
		LOG.debug("Validating plan...");
		for (Run run : plan.getRuns()) {
			if (run.getRunName() == null) {
				throw new ConfigException("No runName has been specified for current run!");
			}
			if (run.getSuccessCondition() == null) {
				throw new ConfigException("No successCondition has been specified for run '" + run.getRunName() + "'!");
			}
			if (plan.isFullscreen()) {
				if (run.getWindowHeightPx() > 0 || run.getWindowHeightPx() > 0) {
					throw new ConfigException("Fullscreen setup in your plan, no custom window size allowed for run '"
							+ run.getRunName() + "'!");
				}
			}
			if (run.getBrowserAction() != null) {
				if (run.getUrl() != null) {
					throw new ConfigException(
							"Browser action setted, no url tag is allowed for run '" + run.getRunName() + "'!");
				}
				if (run.getInputFields() != null) {
					throw new ConfigException("Browser action setted, no input filling tag is allowed for run '"
							+ run.getRunName() + "'!");
				}
				if (run.getBrowserAction().getAction().equals(E_BrowserAction.IFRAME_SWITCH.name())) {
					if (run.getBrowserAction().getElementByXPath() == null) {
						throw new ConfigException("Browser action '" + E_BrowserAction.IFRAME_SWITCH.name()
								+ "' setted, please give me its level property for run '" + run.getRunName() + "'!");
					}
				}
			}
			if (run.getAuthentication() != null) {
				if (run.getAuthentication().getAuthType() == null) {
					throw new ConfigException(
							"Authentication setted, please give me the authType for run '" + run.getRunName() + "'!");
				} else {
					try {
						E_AuthType.valueOf(run.getAuthentication().getAuthType());
					} catch (Exception e) {
						throw new ConfigException("Value '" + run.getAuthentication().getAuthType()
								+ "' for property 'authType' is not allowed!");
					}
					if (run.getAuthentication().getUsername() == null) {
						throw new ConfigException("Authentication setted, please give me the username at authType '"
								+ run.getAuthentication().getAuthType() + "' for run '" + run.getRunName() + "'!");
					}
					if (run.getAuthentication().getPassword() == null) {
						throw new ConfigException("Authentication setted, please give me the password at authType '"
								+ run.getAuthentication().getAuthType() + "' for run '" + run.getRunName() + "'!");
					}
					if (run.getAuthentication().getWaitPromptInSec() == 0) {
						LOG.info(
								"using default waitPromptInSec time: " + NTLMAuthentication.DEFAULT_WAIT_PROMPT_IN_SEC);
						run.getAuthentication().setWaitPromptInSec(NTLMAuthentication.DEFAULT_WAIT_PROMPT_IN_SEC);
					}
				}
			}
		}
	}

}
