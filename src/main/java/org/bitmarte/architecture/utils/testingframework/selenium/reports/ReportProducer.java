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

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("plan", plan);

		reportsUtils.produce("plan-report.html", DefaultSeleniumConfig
				.getConfig().getReportBaseDir() + plan.getPlanName(), root);
	}

	public static void generateIndex(List<Plan> plans) throws Exception {
		ReportsUtils reportsUtils = new ReportsUtils();

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("plans", plans);
		root.put("reportBasePath", DefaultSeleniumConfig.getConfig()
				.getReportBaseDir());

		reportsUtils.produce("index.html", DefaultSeleniumConfig.getConfig()
				.getReportBaseDir(), root);
	}
}
