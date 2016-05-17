package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class ReportGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(ReportGenerator.class);

	public static void generatePlanReport(Plan plan) throws Exception {
		ReportsProducer reportsUtils = new ReportsProducer(ReportsProducer.HTML_TEMPLATE_FOLDER);

		Map<String, Object> root = getCommonRootData();
		root.put("plan", plan);

		reportsUtils.produce("plan-report.html",
				DefaultSeleniumConfig.getConfig().getReportBaseDir() + plan.getPlanName(), root);
	}

	public static void generateIndex(List<Plan> plans) throws Exception {
		ReportsProducer reportsUtils = new ReportsProducer(ReportsProducer.HTML_TEMPLATE_FOLDER);

		Map<String, Object> root = getCommonRootData();
		root.put("plans", plans);

		reportsUtils.produce("index.html", DefaultSeleniumConfig.getConfig().getReportBaseDir(), root);
	}

	/**
	 * Setting common root data
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Map<String, Object> getCommonRootData() throws Exception {
		LOG.debug("Setting common root data...");
		Map<String, Object> root = new HashMap<String, Object>();

		/*
		 * Add here common static data
		 */
		if (DefaultSeleniumConfig.getConfig().getWebTimings() != null) {
			root.put("webTimings", true);
		} else {
			root.put("webTimings", false);
		}

		return root;
	}
}
