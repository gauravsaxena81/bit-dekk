/*
 * Copyright 2013 Contributors of bit-dekk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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
	public void associateRule(int id, IBitSet ruleBitSet, double[] factor) {
		Set<Integer> scenarios = ScenarioUtil.pi(ruleBitSet, scenarioDataHelper);
		if(!scenarios.isEmpty()) {
			for(IBitSet i : ScenarioUtil.neeta(scenarios, ruleBitSet, scenarioDataHelper, dimensionHelper))
				scenarioDataHelper.associateRule(id, i
					, ScenarioUtil.mu(ScenarioUtil.theta(ScenarioUtil.pi(i, scenarioDataHelper), scenarioDataHelper), dimensionHelper, scenarioDataHelper), factor);
		} else 
			scenarioDataHelper.associateRule(id, ruleBitSet, ruleBitSet, factor);
	}
}
