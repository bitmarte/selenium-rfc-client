package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.driver.WebDriverFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.reports.ReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.PlanLoaderRunnable;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.WorkingPlans;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.PlanLoaderFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * @author bitmarte
 *
 */
public class RunTestSuite {

	private static final Logger LOG = LoggerFactory.getLogger(RunTestSuite.class);

	public static void main(String[] args) throws Exception {
		WorkingPlans workingPlans = null;
		BrowserMobProxy proxy = null;
		try {
			DefaultSeleniumConfig.loadConfiguration(args);

			if (DefaultSeleniumConfig.getConfig().isCleanReportBaseDirOnStart()) {
				cleanReportFolder();
			}

			workingPlans = PlanLoaderFactory.getInstance(args[0]).loadWorkingPlans();

			if (DefaultSeleniumConfig.getConfig().getMobProxy() != null) {
				proxy = new BrowserMobProxyServer();
			}

			List<Plan> plan = workingPlans.getPlans();
			for (Plan pl : plan) {
				Thread t = new Thread(
						new PlanLoaderRunnable(
								WebDriverFactory.getInstance(DefaultSeleniumConfig.getConfig().getBrowserMode(),
										DefaultSeleniumConfig.getConfig().getBrowserName(), proxy),
								pl, proxy, workingPlans));
				t.start();
			}

		} catch (Exception e) {
			LOG.error("Generic error! ", e);
		} finally {
			while (true) {
				if (workingPlans.isFinish()) {
					ReportProducer.generateIndex(workingPlans.getPlans());
					if (DefaultSeleniumConfig.getConfig().isCloseBrowserOnFinish()) {
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
		File reportFolder = new File(DefaultSeleniumConfig.getConfig().getReportBaseDir());
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
