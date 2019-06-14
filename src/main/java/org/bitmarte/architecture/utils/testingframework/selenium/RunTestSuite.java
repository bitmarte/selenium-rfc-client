package org.bitmarte.architecture.utils.testingframework.selenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.driver.WebDriverFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan.PlanLoaderRunnable;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan.WorkingPlans;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.WorkingPlansProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.E_ReportType;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.ReportProducerFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.utils.DriverUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * This is the main class
 *
 * @author bitmarte
 */
public class RunTestSuite {

    private static final Logger LOG = LoggerFactory.getLogger(RunTestSuite.class);

    public static void main(String[] args) throws Exception {
        WorkingPlans workingPlans = null;
        BrowserMobProxy proxy = null;
        try {
            SeleniumConfigProvider.loadConfiguration(args);

            if (Boolean.valueOf(SeleniumConfigProvider.getConfig().isCleanReportBaseDirOnStart())) {
                cleanReportFolder();
            }

            workingPlans = WorkingPlansProvider.getWorkingPlans(args[0]);

            if (SeleniumConfigProvider.getConfig().getMobProxy() != null) {
                proxy = new BrowserMobProxyServer();
                DriverUtils.browserMobConfigure(proxy);
            }

            List<Plan> plan = workingPlans.getPlans();
            for (Plan pl : plan) {
                Thread t = new Thread(new PlanLoaderRunnable(
                        WebDriverFactory.getInstance(SeleniumConfigProvider.getConfig().getBrowser().getMode(),
                                SeleniumConfigProvider.getConfig().getBrowser().getName(), proxy),
                        pl, proxy, workingPlans));
                t.start();

                // If concurrentPlans flag is enabled don't wait thread
                if (!Boolean.valueOf(SeleniumConfigProvider.getConfig().isConcurrentPlans())) {
                    t.join();
                }
            }

        } catch (Exception e) {
            LOG.error("Generic error! ", e);
        } finally {
            while (true) {
                if (workingPlans.isFinish()) {
                    ReportProducerFactory.getInstance(E_ReportType.HTML_INDEX, workingPlans.getPlans()).produce();

                    // WebTimings report CSV
                    if (SeleniumConfigProvider.getConfig().getWebTimings() != null) {
                        ReportProducerFactory.getInstance(E_ReportType.CSV_WEBTIMINGS, workingPlans.getPlans())
                                .produce();
                    }

                    if (Boolean.valueOf(SeleniumConfigProvider.getConfig().isCloseBrowserOnFinish())) {
                        try {
                            if (proxy != null) {
                                proxy.stop();
                            }
                        } catch (Throwable t) {
                            LOG.error("Proxy does not stop correctly!", t);
                        }
                    }
                    break;
                }
                Thread.sleep(200);
            }
        }
    }

    /**
     * Clean report folder
     */
    private static void cleanReportFolder() {
        File reportFolder = new File(SeleniumConfigProvider.getConfig().getReportBaseDir());
        try {
            for (File file : reportFolder.listFiles()) {
                LOG.debug("removing file: " + file.getAbsolutePath());
                FileUtils.forceDelete(file);
            }
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }
}
