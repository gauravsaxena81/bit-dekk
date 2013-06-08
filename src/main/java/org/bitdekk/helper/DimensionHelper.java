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
package org.bitdekk.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.BitDekkUtil;

import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.value.ValueType;

public class DimensionHelper {
	private DataHelper dataHelper;
	private DimensionValueHelper dimensionValueHelper;
	
	public DimensionValueHelper getDimensionValueHelper() {
		return dimensionValueHelper;
	}
	public void setDimensionValueHelper(DimensionValueHelper dimensionValueHelper) {
		this.dimensionValueHelper = dimensionValueHelper;
	}
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	public void initializeDimensions(DataTable dataTable) {
		HashMap<String, List<Integer>> dimensionToDimensionValueIdMap = dataHelper.getDimensionToDimensionValueIdMap();
		HashMap<Integer, String> dimensonValueToDimensionMap = dataHelper.getDimensonValueToDimensionMap();
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
					int dimensionId = dimensionValueHelper.getId(label, j.getValue().toString());
					list.add(dimensionId);
					dimensonValueToDimensionMap.put(dimensionId, label);
				}
			}
		}
	}
	public IBitSet getDimensionValuesBitSet(String dimension) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		for(Integer i : dataHelper.getDimensionToDimensionValueIdMap().get(dimension))
			bitSet.set(i);
		return bitSet;
	}
	public String getDimension(int dimensionValueId) {
		return dataHelper.getDimensonValueToDimensionMap().get(dimensionValueId);
	}
}
