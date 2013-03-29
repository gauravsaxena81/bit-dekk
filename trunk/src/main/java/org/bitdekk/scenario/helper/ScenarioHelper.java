package org.bitdekk.scenario.helper;

import java.util.Set;

import org.bitdekk.api.IBitSet;
import org.bitdekk.scenario.ScenarioUtil;

public class ScenarioHelper {
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public void associateRule(int id, IBitSet ruleBitSet, double factor) {
		Set<Integer> scenarios = ScenarioUtil.pi(ruleBitSet, scenarioDataHelper);
		if(!scenarios.isEmpty()) {
			for(IBitSet i : ScenarioUtil.neeta(scenarios, ruleBitSet, scenarioDataHelper, dimensionHelper))
				scenarioDataHelper.associateRule(id, i, ScenarioUtil.mu(ScenarioUtil.theta(ScenarioUtil.pi(i, scenarioDataHelper), scenarioDataHelper), dimensionHelper, scenarioDataHelper));
		} else 
			scenarioDataHelper.associateRule(id, ruleBitSet, ruleBitSet);
	}
	
}
