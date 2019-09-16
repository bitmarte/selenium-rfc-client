package org.bitmarte.architecture.utils.testingframework.selenium.service.report.impl;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_ReportType;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.A_ReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions.ReportProducerException;

import java.util.List;

/**
 * Created by a110282 on 19/06/2019.
 */
public class ExtentReportProducer extends A_ReportProducer {

    public ExtentReportProducer(List<Plan> plans) throws Exception {
        super(plans, E_ReportType.EXTENT_REPORT);
    }

    public void produce() throws Exception {
        try {

            ExtentHtmlReporter html = new ExtentHtmlReporter(SeleniumConfigProvider.getConfig().getReportConfig().getBaseDir() + "/ExtentHtmlReport.html");

            if (SeleniumConfigProvider.getConfig().getReportConfig().getExtentConfig() != null) {
                html.loadConfig(SeleniumConfigProvider.getConfig().getReportConfig().getExtentConfig());
            }

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(html);

            for (Plan pl : plans) {
                ExtentTest planReport = extent.createTest(pl.getPlanName() + " : " + pl.getPlanResult());
                for (Run r : pl.getRuns()) {
                    if (r.getRunReport().getTestResult() != null) {
                        switch (E_TestResult.valueOf(r.getRunReport().getTestResult())) {
                            case SUCCESS:
                                planReport.createNode(r.getRunName()).pass(E_TestResult.SUCCESS.name())
                                        .addScreenCaptureFromPath(pl.getPlanName() + "/screenshots/" + r.getRunName() + "_" + r.getRunReport().getTestResult() + ".png");
                                break;
                            case TIMEOUT:
                            case ERROR:
                                planReport.createNode(r.getRunName()).fail(
                                        MarkupHelper.createLabel("ELEMENT <br/> <i>"
                                                + r.getSuccessCondition().getElement()
                                                + "</i> <br/> <b>DOES NOT " + r.getSuccessCondition().getContentEvaluator()
                                                + " STRING</b> <br/>" + r.getSuccessCondition().getElementContent(), ExtentColor.WHITE)
                                ).addScreenCaptureFromPath(pl.getPlanName() + "/screenshots/" + r.getRunName() + "_" + r.getRunReport().getTestResult() + ".png");
                                break;
                        }
                    } else {
                        planReport.createNode(r.getRunName()).skip(E_TestResult.UNTESTED.name());
                    }
                }
            }

            extent.flush();

        } catch (Exception e) {
            throw new ReportProducerException("Generic error on ExtentReportProducer.produce()!", e);
        }

    }

}
