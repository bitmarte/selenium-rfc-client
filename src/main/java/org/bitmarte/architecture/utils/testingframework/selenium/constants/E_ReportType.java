package org.bitmarte.architecture.utils.testingframework.selenium.constants;

/**
 * @author bitmarte
 */
public enum E_ReportType {

    CSV_WEBTIMINGS("data"), HTML_PLAN("html"), HTML_INDEX("html"), EXTENT_REPORT("extent");

    private final String templateRootName;

    E_ReportType(String templateRootName) {
        this.templateRootName = templateRootName;
    }

    public String getTemplateRootName() {
        return templateRootName;
    }

}
