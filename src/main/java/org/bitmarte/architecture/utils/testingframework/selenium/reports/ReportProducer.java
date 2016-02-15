package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class ReportProducer {

	private static final Logger LOG = LoggerFactory
			.getLogger(ReportProducer.class);

	public static void generatePlanReport(Plan plan) throws Exception {
		ReportsUtils reportsUtils = new ReportsUtils();

		Map<String, Object> root = getCommonRootData();
		root.put("plan", plan);

		reportsUtils.produce("plan-report.html", DefaultSeleniumConfig
				.getConfig().getReportBaseDir() + plan.getPlanName(), root);
	}

	public static void generateIndex(List<Plan> plans) throws Exception {
		ReportsUtils reportsUtils = new ReportsUtils();

		Map<String, Object> root = getCommonRootData();
		root.put("plans", plans);

		reportsUtils.produce("index.html", DefaultSeleniumConfig.getConfig()
				.getReportBaseDir(), root);
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
		root.put("reportBasePath", DefaultSeleniumConfig.getConfig()
				.getReportBaseDir());

		if (LOG.isDebugEnabled()) {
			for (String rootKey : root.keySet()) {
				LOG.debug("adding '" + rootKey + "' element");
			}
		}
		return root;
	}
}
