package org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl;

import java.io.File;
import java.io.FileFilter;

import javax.naming.ConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.WorkingPlans;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.A_PlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.UnmarshallerFactory;

import com.rits.cloning.Cloner;

/**
 * @author bitmarte
 *
 */
public class DefaultPlanLoader extends A_PlanLoader {

	public DefaultPlanLoader(String basePath) {
		super(basePath);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.service.loader.
	 * I_PlanLoader#loadWorkingPlans()
	 */
	public WorkingPlans loadWorkingPlans() throws Exception {
		WorkingPlans workingPlans = new WorkingPlans();
		try {
			File configFolder = new File(this.basePath + "/plans");
			File[] plans = configFolder.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					boolean checker = false;
					if (StringUtils.equalsIgnoreCase(FilenameUtils.getExtension(pathname.getAbsolutePath()), "xml")) {
						LOG.debug("Load '" + pathname.getAbsolutePath() + "'");
						checker = true;
					}
					return checker;
				}
			});

			if (plans.length > 0) {
				Plan plan = null;
				Cloner cloner = null;
				for (File file : plans) {
					plan = (Plan) UnmarshallerFactory.getInstance(Plan.class).unmarshall(file);
					if (plan.getConcurrentExecutors() > 1) {
						cloner = new Cloner();
						for (int i = 1; i <= plan.getConcurrentExecutors(); i++) {
							Plan pl = cloner.deepClone(plan);
							pl.setPlanName(pl.getPlanName() + "_" + i);
							workingPlans.pushWorkingPlan(pl);
						}
					} else {
						workingPlans.pushWorkingPlan(plan);
					}
				}
			} else {
				throw new ConfigurationException("No plans exist!");
			}

			return workingPlans;
		} catch (Exception e) {
			throw e;
		}
	}

}
