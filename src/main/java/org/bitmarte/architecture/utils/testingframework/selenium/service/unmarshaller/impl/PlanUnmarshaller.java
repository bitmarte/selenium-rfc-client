package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Authentication;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.SuccessCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.BackAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ComboFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.DoubleClickAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.ForwardAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.GoToUrlAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.IFrameSwitchAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.InputFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RadioFillAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RefreshAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveAllCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.RemoveCookiesAction;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.WindowResizeAction;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.A_Unmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;

import com.thoughtworks.xstream.XStream;

/**
 * @author bitmarte
 *
 */
public class PlanUnmarshaller extends A_Unmarshaller {

	public Plan unmarshall(File xmlFileInput) throws Exception {
		try {
			XStream xStream = new XStream();

			xStream.processAnnotations(Plan.class);
			xStream.processAnnotations(Authentication.class);
			xStream.processAnnotations(Run.class);
			xStream.processAnnotations(SuccessCondition.class);
			xStream.processAnnotations(ErrorCondition.class);
			// BrowserActions
			xStream.processAnnotations(BackAction.class);
			xStream.processAnnotations(ClickAction.class);
			xStream.processAnnotations(DoubleClickAction.class);
			xStream.processAnnotations(ComboFillAction.class);
			xStream.processAnnotations(ForwardAction.class);
			xStream.processAnnotations(GoToUrlAction.class);
			xStream.processAnnotations(IFrameSwitchAction.class);
			xStream.processAnnotations(InputFillAction.class);
			xStream.processAnnotations(RadioFillAction.class);
			xStream.processAnnotations(RefreshAction.class);
			xStream.processAnnotations(RemoveAllCookiesAction.class);
			xStream.processAnnotations(RemoveCookiesAction.class);
			xStream.processAnnotations(WindowResizeAction.class);

			Plan plan = (Plan) xStream.fromXML(xmlFileInput);

			ValidatorHandler.execute(plan);

			String planFileName = StringUtils.substring(xmlFileInput.getName(), 0,
					xmlFileInput.getName().lastIndexOf("."));
			plan.setPlanName(planFileName);

			return plan;
		} catch (Exception e) {
			throw e;
		}
	}

}
