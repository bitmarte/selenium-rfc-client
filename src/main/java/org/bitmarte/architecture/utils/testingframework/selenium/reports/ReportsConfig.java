package org.bitmarte.architecture.utils.testingframework.selenium.reports;

/**
 * @author bitmarte
 *
 */
public class ReportsConfig {

	private String bootstrapBasePath;
	private String pluginsBasePath;
	private String distBasePath;

	public ReportsConfig() {
		this.bootstrapBasePath = "theme/bootstrap";
		this.pluginsBasePath = "theme/plugins";
		this.distBasePath = "theme/dist";
	}

	public String getBootstrapBasePath() {
		return bootstrapBasePath;
	}

	public String getPluginsBasePath() {
		return pluginsBasePath;
	}

	public String getDistBasePath() {
		return distBasePath;
	}

}
