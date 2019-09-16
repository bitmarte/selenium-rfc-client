package org.bitmarte.architecture.utils.testingframework.selenium.service.report.impl;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_ReportType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.A_ReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions.ReportProducerException;

import java.util.List;
import java.util.Map;

/**
 * @author bitmarte
 */
public class HtmlPlanIndexReportProducer extends A_ReportProducer {

    public HtmlPlanIndexReportProducer(List<Plan> plans) throws Exception {
        super(plans, E_ReportType.HTML_INDEX);
    }

    public void produce() throws Exception {
        try {
            Map<String, Object> root = super.getCommonRootData();
            root.put("plans", plans);

            super.produce("index.html", SeleniumConfigProvider.getConfig().getReportConfig().getBaseDir(), root);
        } catch (Exception e) {
            throw new ReportProducerException("Generic error on HtmlPlanIndexReportProducer.produce()!", e);
        }

    }

}
