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

import java.util.HashMap;
import java.util.List;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.OpenBitSet;

public class DimensionHelper {
	private ScenarioDataHelper scenarioDataHelper;
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public void initializeDimensions(HashMap<String, List<Integer>> dimensionToDimensionValueIdMap) {
		scenarioDataHelper.setDimensionToDimensionValueIdMap(dimensionToDimensionValueIdMap);
	}
	public IBitSet getDimensionValuesBitSet(String dimension) {
		IBitSet bitSet = new OpenBitSet();
		for(Integer i : scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension))
			bitSet.set(i);
		return bitSet;
	}
	public String getDimension(int dimensionValueId) {
		return scenarioDataHelper.getDimensonValueToDimensionMap().get(dimensionValueId);
	}
	public void associateDimensionValue(String dimension, int dimensionValueId) {
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(dimensionValueId);
		scenarioDataHelper.getDimensonValueToDimensionMap().put(dimensionValueId, dimension);
	}
}
