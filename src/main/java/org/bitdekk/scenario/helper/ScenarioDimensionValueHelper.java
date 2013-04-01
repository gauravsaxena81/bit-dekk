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

import java.util.List;

import org.bitdekk.helper.DataHelper;

public class ScenarioDimensionValueHelper {
	private DataHelper dataHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}

	public void addDimensionValue(String dimension, String dimensionValue, int id) {
		dataHelper.getDimensionValueMap().put(dimensionValue, id);
		dataHelper.getIdToDimensionValueMap().put(id, dimensionValue);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
	}
	public void deleteDimensionValue(String dimension, String dimensionValue, int id) {
		dataHelper.getDimensionValueMap().remove(dimensionValue);
		dataHelper.getIdToDimensionValueMap().remove(id);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
	}
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension);
	}
}
