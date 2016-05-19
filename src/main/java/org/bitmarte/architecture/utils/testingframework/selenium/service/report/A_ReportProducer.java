package org.bitmarte.architecture.utils.testingframework.selenium.service.report;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.report.exceptions.ReportProducerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author bitmarte
 *
 */
public abstract class A_ReportProducer implements I_ReportProducer {

	protected List<Plan> plans = null;
	protected Configuration configuration = null;

	protected static final Logger LOG = LoggerFactory.getLogger(A_ReportProducer.class);

	public A_ReportProducer(List<Plan> plans, E_ReportType reportType) throws Exception {
		try {
			this.plans = plans;

			// Setup Freemarker configuration
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
			configuration.setClassLoaderForTemplateLoading(A_ReportProducer.class.getClassLoader(),
					"/freemarker/reports/" + reportType.getTemplateRootName());
			configuration.setDefaultEncoding("UTF-8");

			if (LOG.isDebugEnabled()) {
				configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			} else {
				configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			}

			this.configuration = configuration;
		} catch (Exception e) {
			throw new ReportProducerException("Generic Error on A_ReportProducer constructor!", e);
		}
	}

	/**
	 * Merge template with data and produce the output file
	 * 
	 * @param templateName
	 * @param outputFilePath
	 * @param data
	 * @throws Exception
	 */
	protected void produce(String templateName, String outputFilePath, Map<String, Object> data) throws Exception {
		try {
			Template template = this.configuration.getTemplate(templateName);
			Writer fileOutputStream = new FileWriter(outputFilePath + "/" + templateName);
			template.process(data, fileOutputStream);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Setting common root data
	 * 
	 * @return
	 * @throws Exception
	 */
	protected static Map<String, Object> getCommonRootData() throws Exception {
		try {
			LOG.debug("Setting common root data...");
			Map<String, Object> root = new HashMap<String, Object>();

			/*
			 * Add here common static data
			 */
			if (SeleniumConfigProvider.getConfig().getWebTimings() != null) {
				root.put("webTimings", true);
			} else {
				root.put("webTimings", false);
			}

			return root;
		} catch (Exception e) {
			throw e;
		}

	}

}
