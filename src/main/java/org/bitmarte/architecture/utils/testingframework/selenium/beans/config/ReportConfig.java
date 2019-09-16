package org.bitmarte.architecture.utils.testingframework.selenium.beans.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * This is the reporter configuration.
 * <p>
 * Thanks to extentreports project
 * [https://extentreports.com/]
 *
 * @author bitmarte
 */
@XStreamAlias("report")
public class ReportConfig {

    /**
     * @see org.bitmarte.architecture.utils.testingframework.selenium.constants.E_ReportType
     */
    @XStreamAlias("type")
    private String type;

    /**
     * Path to extent reporter XML configuration
     */
    @XStreamAlias("extentConfig")
    private String extentConfig;

    /**
     * Where report will be saved, only path
     */
    @XStreamAlias("baseDir")
    private String baseDir;

    /**
     * If <code>true</code> the screenshot contain highlighted element which fail on success condition evaluation.
     * Default value is <code>false</code>
     */
    @XStreamAlias("highlightOnError")
    private String highlightOnError;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtentConfig() {
        return extentConfig;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String isHighlightOnError() {
        return highlightOnError;
    }

    public void setHighlightOnError(String highlightOnError) {
        this.highlightOnError = highlightOnError;
    }
}
