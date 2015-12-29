package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author bitmarte
 *
 */
public class ReportsUtils {

	private static final Logger LOG = LoggerFactory
			.getLogger(ReportsUtils.class);

	public static void generate(Plan plan) throws Exception {
		Configuration configuration = new Configuration(
				Configuration.VERSION_2_3_23);
		configuration.setClassLoaderForTemplateLoading(
				ReportsUtils.class.getClassLoader(), "/freemarker/templates");
		configuration.setDefaultEncoding("UTF-8");

		if (LOG.isDebugEnabled()) {
			configuration
					.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		} else {
			configuration
					.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		}

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("plan", plan);

		Template template = configuration.getTemplate("index.html");
		Writer fileOutputStream = new FileWriter(DefaultSeleniumConfig
				.getConfig().getReportBaseDir()
				+ plan.getPlanName()
				+ "/index.html");
		template.process(root, fileOutputStream);
	}
}
