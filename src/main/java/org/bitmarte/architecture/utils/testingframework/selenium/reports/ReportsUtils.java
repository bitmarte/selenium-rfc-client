package org.bitmarte.architecture.utils.testingframework.selenium.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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

	private Configuration configuration = null;

	public ReportsUtils() {
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
	public void produce(String templateName, String outputFilePath,
			Map<String, Object> data) throws Exception {
		try {
			this.copyTheme(outputFilePath);

			Template template = this.configuration.getTemplate(templateName);
			Writer fileOutputStream = new FileWriter(outputFilePath + "/"
					+ templateName);
			template.process(data, fileOutputStream);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create theme
	 * 
	 * @param outputFilePath
	 * @throws Exception
	 */
	private void copyTheme(String outputFilePath) throws Exception {
		try {
			FileUtils.copyDirectory(
					new File(getClass().getResource("/freemarker/theme")
							.getFile()), new File(outputFilePath + "/theme"));
		} catch (Exception e) {
			throw e;
		}
	}
}
