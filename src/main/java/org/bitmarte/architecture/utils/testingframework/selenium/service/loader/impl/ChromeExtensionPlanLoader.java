package org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.E_ContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.A_PlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.E_PlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import com.rits.cloning.Cloner;

/**
 * @author bitmarte
 *
 */
public class ChromeExtensionPlanLoader extends A_PlanLoader {

	private static final String NO_VALUE_JSON = "NO_VALUE";

	public ChromeExtensionPlanLoader(String basePath) {
		super(basePath);
		// TODO Auto-generated constructor stub
		LOG.info("using ChromeExtensionPlanLoader...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.service.loader.
	 * I_PlanLoader#loadWorkingPlans()
	 */
	public List<Plan> loadPlans() throws Exception {
		List<Plan> planList = new ArrayList<Plan>();
		try {
			File configFolder = new File(this.basePath + "/plans");
			File[] plans = configFolder.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					boolean checker = false;
					if (StringUtils.equalsIgnoreCase(FilenameUtils.getExtension(pathname.getAbsolutePath()), "json")) {
						LOG.debug("Load '" + pathname.getAbsolutePath() + "' from Chrome Extension Export...");
						checker = true;
					}
					return checker;
				}
			});

			if (plans.length > 0) {
				for (File file : plans) {
					JsonArray bActions = Json.parse(FileUtils.readFileToString(file)).asArray();
					Plan plan = new Plan();
					plan.setPlanName(file.getName());
					List<Run> runs = new ArrayList<Run>();
					List<A_BrowserAction> browserActions = new ArrayList<A_BrowserAction>();
					Cloner clone = null;
					Run run = null;
					SuccessCondition successCondition = null;

					// force window size
					WindowResizeAction wra = new WindowResizeAction();
					wra.setWidthPx(1440);
					wra.setHeightPx(900);
					browserActions.add(wra);

					int i = 1;
					for (JsonValue bAction : bActions) {
						switch (bAction.asObject().getString("browserAction", NO_VALUE_JSON)) {
						case "GO_TO_URL":
							GoToUrlAction goToUrlAction = new GoToUrlAction();
							goToUrlAction.setUrl(bAction.asObject().getString("content", NO_VALUE_JSON));
							browserActions.add(goToUrlAction);
							break;
						case "CLICK":
							ClickAction clickAction = new ClickAction();
							clickAction.setElement(bAction.asObject().getString("xpath", NO_VALUE_JSON));
							browserActions.add(clickAction);
							break;
						case "SET":
							InputFillAction inputFillAction = new InputFillAction();
							inputFillAction.setElement(bAction.asObject().getString("xpath", NO_VALUE_JSON));
							inputFillAction.setValue(bAction.asObject().getString("content", NO_VALUE_JSON));
							browserActions.add(inputFillAction);
							break;
						case "SUCCESS_CONDITION_EQUALS":
							run = new Run();
							run.setRunName("run_" + i);
							successCondition = new SuccessCondition();
							successCondition.setElement(bAction.asObject().getString("xpath", NO_VALUE_JSON));
							successCondition.setElementContent(bAction.asObject().getString("content", NO_VALUE_JSON));
							successCondition.setContentEvaluator(E_ContentEvaluator.EQUALS.name());
							run.setSuccessCondition(successCondition);
							clone = new Cloner();
							run.setBrowserActions(clone.deepClone(browserActions));
							runs.add(run);
							browserActions = new ArrayList<A_BrowserAction>();
							i++;
							break;
						case "SUCCESS_CONDITION_CONTAINS":
							run = new Run();
							run.setRunName(StringUtils.substring(file.getName(), 0, file.getName().lastIndexOf("."))
									+ "_" + i);
							successCondition = new SuccessCondition();
							successCondition.setElement(bAction.asObject().getString("xpath", NO_VALUE_JSON));
							successCondition.setElementContent(bAction.asObject().getString("content", NO_VALUE_JSON));
							successCondition.setContentEvaluator(E_ContentEvaluator.CONTAINS.name());
							run.setSuccessCondition(successCondition);
							clone = new Cloner();
							run.setBrowserActions(clone.deepClone(browserActions));
							runs.add(run);
							browserActions = new ArrayList<A_BrowserAction>();
							i++;
							break;
						default:
							throw new Exception("Unknown browserAction!");
						}
					}
					plan.setRuns(runs);

					ValidatorHandler.execute(plan);
					String planFileName = StringUtils.substring(file.getName(), 0, file.getName().lastIndexOf("."));
					plan.setPlanName(planFileName);

					planList.add(plan);
				}
			} else {
				LOG.warn("No plans loaded for PlanLoader '" + E_PlanLoader.CHROME_EXTESION_JSON + "'!");
			}

			return planList;
		} catch (Exception e) {
			throw e;
		}
	}

}
