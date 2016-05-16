package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller;

import java.rmi.UnmarshalException;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.Config;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl.ConfigUnmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl.PlanUnmarshaller;

/**
 * @author bitmarte
 *
 */
public class UnmarshallerFactory {

	public static I_Umarshaller getInstance(Class<?> in) throws Exception {
		if (in.isAssignableFrom(Config.class)) {
			return new ConfigUnmarshaller();
		}
		if (in.isAssignableFrom(Plan.class)) {
			return new PlanUnmarshaller();
		}

		throw new UnmarshalException("Unknown object type: " + in.getClass().getName());
	}

}
