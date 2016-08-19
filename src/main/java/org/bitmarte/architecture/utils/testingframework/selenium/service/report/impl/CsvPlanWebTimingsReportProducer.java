package org.bitmarte.architecture.utils.testingframework.selenium.service.report.impl;

import java.util.List;
import java.util.Map;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.A_ReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.E_ReportType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions.ReportProducerException;

/**
 * @author bitmarte
 *
 */
public class CsvPlanWebTimingsReportProducer extends A_ReportProducer {

	public CsvPlanWebTimingsReportProducer(List<Plan> plans) throws Exception {
		super(plans, E_ReportType.CSV_WEBTIMINGS);
	}

	public void produce() throws Exception {
		try {
			Map<String, Object> root = super.getCommonRootData();
			root.put("plans", plans);

			super.produce("web-timings.csv", SeleniumConfigProvider.getConfig().getReportBaseDir(), root);
		} catch (Exception e) {
			throw new ReportProducerException("Generic error on CsvPlanWebTimingsReportProducer.produce()!", e);
		}

	}

}
