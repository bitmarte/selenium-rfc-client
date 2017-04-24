package org.bitmarte.architecture.utils.testingframework.selenium.service.validator;

/**
 * This is the validator interface
 * 
 * @author bitmarte
 */
public interface I_Validator {

	/**
	 * The validate method
	 * 
	 * @throws Exception
	 */
	public void validate() throws Exception;

	/**
	 * This method set the default values for non required parameters
	 * 
	 * @throws Exception
	 */
	public void setDefaultValue() throws Exception;

}
