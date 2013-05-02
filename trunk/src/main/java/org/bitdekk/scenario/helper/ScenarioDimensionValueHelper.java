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
import java.util.Set;

import org.bitdekk.DataLayer;
import org.bitdekk.api.IBitSet;
import org.bitdekk.helper.DataHelper;
import org.bitdekk.util.OpenBitSet;

public class ScenarioDimensionValueHelper {
	private DataHelper dataHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	private DataLayer dataLayer;
	
	public DataLayer getDataLayer() {
		return dataLayer;
	}
	public void setDataLayer(DataLayer dataLayer) {
		this.dataLayer = dataLayer;
	}
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
	public void createDimensionValue(String dimension, String dimensionValue, int id) {
		dataHelper.getDimensionValueMap().put(dimensionValue, id);
		dataHelper.getIdToDimensionValueMap().put(id, dimensionValue);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
		scenarioDataHelper.getDimensonValueToDimensionMap().put(id, dimension);
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue, int id) {
		Integer remove = dataHelper.getDimensionValueMap().remove(dimensionValue);
		if(remove != null) {
			dataHelper.getIdToDimensionValueMap().remove(id);
			scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).remove(Integer.valueOf(id));
			return true;
		} else
			return false;
	}
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension);
	}
	public IBitSet getBitSet(String[] dimensionValues) {
		IBitSet bitSet = new OpenBitSet();
		for(String i : dimensionValues) {
			int id = dataLayer.getDimensionId(i);
			if(id > -1)
				bitSet.set(id);
			else {
				id = getId(i);
				if(id > -1)
					bitSet.set(id);
				else
					throw new IllegalArgumentException("Dimension " + i + " not found"); 
			}
		}
		return bitSet;
	}
	public int getId(String dimensionValue) {
		Integer id = dataHelper.getDimensionValueMap().get(dimensionValue);
		if(id != null)
			return id;
		else
			return -1;
	}
	public String getDimensionValue(int id) {
		String dimensionValue = dataLayer.getDimensionValue(id);
		if(dimensionValue != null)
			return dimensionValue;
		else {
			dimensionValue = dataHelper.getIdToDimensionValueMap().get(id);
			if(dimensionValue != null)
				return dimensionValue;
			else
				throw new IllegalArgumentException("Dimension for " + id + " not found");
		}
			
	}
	public Set<Integer> getDimensionValueIds() {
		return dataHelper.getIdToDimensionValueMap().keySet();
	}
}
