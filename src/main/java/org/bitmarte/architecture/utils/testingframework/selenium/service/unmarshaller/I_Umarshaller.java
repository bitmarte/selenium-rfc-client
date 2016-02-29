package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller;

import java.io.File;

/**
 * @author bitmarte
 *
 */
public interface I_Umarshaller {

	public Object unmarshall(File xmlFileInput) throws Exception;
}
