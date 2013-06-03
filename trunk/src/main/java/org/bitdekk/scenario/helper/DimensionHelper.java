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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bitdekk.DataLayer;
import org.bitdekk.api.IBitSet;
import org.bitdekk.util.BitDekkUtil;

import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.value.ValueType;

public class DimensionHelper {
	private ScenarioDataHelper scenarioDataHelper;
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
	public void initializeDimensions(DataTable dataTable) {
		HashMap<String, List<Integer>> dimensionToDimensionValueIdMap = scenarioDataHelper.getDimensionToDimensionValueIdMap();
		HashMap<Integer, String> dimensonValueToDimensionMap = scenarioDataHelper.getDimensonValueToDimensionMap();
		Comparator<TableCell> comparator = new Comparator<TableCell>() {
			@Override
			public int compare(TableCell o1, TableCell o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};
		for(int i = 0; i < dataTable.getNumberOfColumns(); i++) {
			if(!dataTable.getColumnDescription(i).getType().equals(ValueType.NUMBER)) {
				String label = dataTable.getColumnDescription(i).getLabel();
				for(TableCell j : dataTable.getColumnDistinctCellsSorted(i, comparator)) {
					List<Integer> list = dimensionToDimensionValueIdMap.get(label);
					if(list == null) {
						list = new ArrayList<Integer>();
						dimensionToDimensionValueIdMap.put(label, list);
					}
					int dimensionId = dataLayer.getDimensionId(label, j.getValue().toString());
					//System.out.println("---xxx---" + j.getValue().toString() + "," + dimensionId + "," + list);
					System.out.println("---xxx---" + list + "," + System.identityHashCode(dimensionToDimensionValueIdMap));
					list.add(dimensionId);
					dimensonValueToDimensionMap.put(dimensionId, label);
				}
			}
		}
	}
	public IBitSet getDimensionValuesBitSet(String dimension) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		for(Integer i : scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension))
			bitSet.set(i);
		return bitSet;
	}
	public String getDimension(int dimensionValueId) {
		return scenarioDataHelper.getDimensonValueToDimensionMap().get(dimensionValueId);
	}
	public void createDimensionValue(String dimension, int dimensionValueId) {
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(dimensionValueId);
		scenarioDataHelper.getDimensonValueToDimensionMap().put(dimensionValueId, dimension);
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue, int id) {
		boolean remove = scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).remove(Integer.valueOf(id));
		if(remove) {
			scenarioDataHelper.getDimensonValueToDimensionMap().remove(id);
			return true;
		} else
			return false;
	}
}
