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

import org.bitdekk.api.IBitSet;
import org.bitdekk.helper.DataHelper;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.util.BitDekkUtil;

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
	public int createDimensionValue(String dimension, String dimensionValue) {
		String generateDimensionValueString = BitDekkUtil.generateDimensionValueString(dimension, dimensionValue);
		int id = dataHelper.getId();
		dataHelper.addToId();
		dataHelper.getDimensionValueMap().put(generateDimensionValueString, id);
		dataHelper.getIdToDimensionValueMap().put(id, generateDimensionValueString);
		return id;
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue, int id) {
		Integer remove = dataHelper.getDimensionValueMap().remove(BitDekkUtil.generateDimensionValueString(dimension, dimensionValue));
		if(remove != null) {
			dataHelper.getIdToDimensionValueMap().remove(id);
			return true;
		} else
			return false;
	}
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension);
	}
	public IBitSet getBitSet(DimensionValue[] dimensionValues) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		for(DimensionValue i : dimensionValues) {
			Integer id = dataHelper.getDimensionValueMap().get(BitDekkUtil.generateDimensionValueString(i.getDimension(), i.getDimensionValue()));
			if(id != null)
				bitSet.set(id);
			else
				throw new IllegalArgumentException("Dimension value " + i.getDimension() + "-" + i.getDimensionValue() + " not found"); 
		}
		return bitSet;
	}
	public int getId(String dimension, String dimensionValue) {
		Integer id = dataHelper.getDimensionValueMap().get(BitDekkUtil.generateDimensionValueString(dimension, dimensionValue));
		if(id != null)
			return id;
		else
			return -1;
	}
	public DimensionValue getDimensionValue(int id) {
		String string = dataHelper.getIdToDimensionValueMap().get(id);
		return new DimensionValue(string.substring(0, string.indexOf('-') - 1), string.substring(string.indexOf('-') + 1));
	}
	public Set<Integer> getDimensionValueIds() {
		return dataHelper.getIdToDimensionValueMap().keySet();
	}
}
