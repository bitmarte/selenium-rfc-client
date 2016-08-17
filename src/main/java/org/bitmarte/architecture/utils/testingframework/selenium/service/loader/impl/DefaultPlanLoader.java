package org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.A_PlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.E_PlanLoader;
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
		LOG.info("using DefaultPlanLoader...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bitmarte.architecture.utils.testingframework.selenium.service.loader.
	 * I_PlanLoader#loadWorkingPlans()
	 */
	public List<Plan> loadPlans() throws Exception {
		List<Plan> planList = new ArrayList<Plan>();
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
					if (plan.getExecutions() > 1) {
						cloner = new Cloner();
						for (int i = 1; i <= plan.getExecutions(); i++) {
							Plan pl = cloner.deepClone(plan);
							pl.setPlanName(pl.getPlanName() + "_" + i);
							planList.add(pl);
						}
					} else {
						planList.add(plan);
					}
				}
			} else {
				LOG.warn("No plans loaded for PlanLoader '" + E_PlanLoader.DEFAULT_XML + "'!");
			}

			return planList;
		} catch (Exception e) {
			throw e;
		}
	}

}
