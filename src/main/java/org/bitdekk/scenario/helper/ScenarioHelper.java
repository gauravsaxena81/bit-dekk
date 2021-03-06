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
import org.bitdekk.helper.DataHelper;
import org.bitdekk.helper.DimensionHelper;
import org.bitdekk.scenario.ScenarioUtil;

public class ScenarioHelper {
	private ScenarioDataHelper scenarioDataHelper;
	private DataHelper dataHelper;
	private DimensionHelper dimensionHelper;
	
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
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
			for(IBitSet i : ScenarioUtil.neeta(scenarios, ruleBitSet, dataHelper, dimensionHelper)) {
				IBitSet key = i.clone();
				key.set(id);
				scenarioDataHelper.associateRule(id, key
					, ScenarioUtil.mu(ScenarioUtil.theta(ScenarioUtil.pi(i, scenarioDataHelper), ruleBitSet, scenarioDataHelper, dataHelper, dimensionHelper), dimensionHelper
					, dataHelper), factor);
			}
		} else {
			IBitSet key = ruleBitSet.clone();
			key.set(id);
			scenarioDataHelper.associateRule(id, key, ruleBitSet, factor);
		}
	}
	public boolean deleteRule(int id, IBitSet ruleBitSet) {
		IBitSet clone = ruleBitSet.clone();
		clone.set(id);
		return scenarioDataHelper.deleteRule(id, clone);
	}
}
