package org.bitmarte.architecture.utils.testingframework.selenium.service.validator;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ComboFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.IFrameSwitchAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RadioFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.ConfigValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.PlanValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.ClickActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.ComboFillActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.GoToUrlActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.IFrameSwitchActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.InputFillActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.RadioFillActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.RemoveCookiesActionValidator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.impl.action.WindowResizeActionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class ValidatorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorHandler.class);

	public static void execute(Object inValidation) throws Exception {
		I_Validator v = null;

		try {
			if (inValidation instanceof Config) {
				v = new ConfigValidator((Config) inValidation);
			} else if (inValidation instanceof Plan) {
				v = new PlanValidator((Plan) inValidation);
			}

			// BrowserActions validator
			else if (inValidation instanceof ClickAction) {
				v = new ClickActionValidator((ClickAction) inValidation);
			} else if (inValidation instanceof ComboFillAction) {
				v = new ComboFillActionValidator((ComboFillAction) inValidation);
			} else if (inValidation instanceof GoToUrlAction) {
				v = new GoToUrlActionValidator((GoToUrlAction) inValidation);
			} else if (inValidation instanceof IFrameSwitchAction) {
				v = new IFrameSwitchActionValidator((IFrameSwitchAction) inValidation);
			} else if (inValidation instanceof InputFillAction) {
				v = new InputFillActionValidator((InputFillAction) inValidation);
			} else if (inValidation instanceof RadioFillAction) {
				v = new RadioFillActionValidator((RadioFillAction) inValidation);
			} else if (inValidation instanceof RemoveCookiesAction) {
				v = new RemoveCookiesActionValidator((RemoveCookiesAction) inValidation);
			} else if (inValidation instanceof WindowResizeAction) {
				v = new WindowResizeActionValidator((WindowResizeAction) inValidation);
			}

			// no validator
			else {
				LOG.warn("No validator for object '" + inValidation.getClass().getCanonicalName() + "'");
			}

			// validate
			if (v != null) {
				v.validate();
				v.setDefaultValue();
			}
		} catch (Exception e) {
			throw e;
		}

	}
}
