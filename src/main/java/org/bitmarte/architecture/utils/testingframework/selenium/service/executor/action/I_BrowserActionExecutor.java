package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.action;

/**
 * This is the browser action executor interface
 * 
 * @author bitmarte
 */
public interface I_BrowserActionExecutor {

	/**
	 * The executor implemented into abstract one,
	 * {@link A_BrowserActionExecutor#execute()}
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception;

	/**
	 * The laucher implemented into each concrete executors
	 * 
	 * @throws Exception
	 */
	public void launcher() throws Exception;
}
