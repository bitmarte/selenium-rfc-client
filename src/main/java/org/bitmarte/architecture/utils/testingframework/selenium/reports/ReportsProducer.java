package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author bitmarte
 *
 */
public class ReportsProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ReportsProducer.class);
	
	public static final String HTML_TEMPLATE_FOLDER = "html";
	public static final String DATA_TEMPLATE_FOLDER = "data";

	private Configuration configuration = null;

	public ReportsProducer(String dirArchive) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setClassLoaderForTemplateLoading(ReportsProducer.class.getClassLoader(),
				"/freemarker/reports/" + dirArchive);
		configuration.setDefaultEncoding("UTF-8");

		if (LOG.isDebugEnabled()) {
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		} else {
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		}

		this.configuration = configuration;
	}

	/**
	 * Merge template with data and produce the output file
	 * 
	 * @param templateName
	 * @param outputFilePath
	 * @param data
	 * @throws Exception
	 */
	public void produce(String templateName, String outputFilePath, Map<String, Object> data) throws Exception {
		try {
			Template template = this.configuration.getTemplate(templateName);
			Writer fileOutputStream = new FileWriter(outputFilePath + "/" + templateName);
			template.process(data, fileOutputStream);
		} catch (Exception e) {
			throw e;
		}
	}
}
