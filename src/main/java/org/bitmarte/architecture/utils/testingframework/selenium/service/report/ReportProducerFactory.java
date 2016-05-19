package org.bitmarte.architecture.utils.testingframework.selenium.service.report;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions.ReportProducerException;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.impl.HtmlPlanIndexReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.impl.HtmlPlanReportProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class ReportProducerFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ReportProducerFactory.class);

	public static I_ReportProducer getInstance(E_ReportType reportType, List<Plan> plans) throws Exception {
		try {
			LOG.info("Producing report type '" + reportType.name() + "' ...");
			switch (reportType) {
			case HTML_PLAN:
				return new HtmlPlanReportProducer(plans);
			case HTML_INDEX:
				return new HtmlPlanIndexReportProducer(plans);

			default:
				throw new ReportProducerException(
						"No ReportProducer implementation for reportType '" + reportType.name() + "'");
			}
		} catch (Exception e) {
			throw new ReportProducerException("Generic error on ReportProducerFactory.getInstance()!", e);
		}
	}

}
