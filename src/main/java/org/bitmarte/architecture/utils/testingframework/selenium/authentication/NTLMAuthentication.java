package org.bitmarte.architecture.utils.testingframework.selenium.authentication;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class NTLMAuthentication implements Runnable {

	public static final int DEFAULT_WAIT_PROMPT_IN_SEC = 5;

	private static final Logger LOG = LoggerFactory.getLogger(NTLMAuthentication.class);

	private Run run;

	public NTLMAuthentication(Run run) {
		this.run = run;
	}

	public void run() {
		try {
			// wait - increase this wait period if required
			Thread.sleep(this.run.getAuthentication().getWaitPromptInSec() * 1000);

			// create robot for keyboard operations
			Robot rb = new Robot();

			// Enter user name by ctrl-v
			StringSelection u = new StringSelection(this.run.getAuthentication().getUsername());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(u, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// tab to password entry field
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			// Enter password by ctrl-v
			StringSelection pwd = new StringSelection(this.run.getAuthentication().getPassword());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			// press enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);

			// wait
			Thread.sleep(5000);
		} catch (Exception e) {
			LOG.error("Error on NTLMAuthentication!", e);
		}
	}

}
