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

import java.util.HashMap;
import java.util.Set;

import org.bitdekk.util.OpenBitSet;

import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.TextValue;
import com.google.visualization.datasource.datatable.value.ValueType;


public class DimensionValueHelper {
	
	private DataHelper dataHelper;
	
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	public void initialize(HashMap<String, Integer> dimensionMap) {
		dataHelper.setDimensionValueMap(dimensionMap);
		HashMap<Integer, String> idMap = new HashMap<Integer, String>();
		for(String i : dimensionMap.keySet())
			idMap.put(dimensionMap.get(i), i);
		dataHelper.setIdToDimensionValueMap(idMap);
	}
	public void initialize(DataTable dataTable) {
		HashMap<String, Integer> dimensionMap = dataHelper.getDimensionValueMap();
		int index = 0;
		for(TableRow i : dataTable.getRows())
			for(TableCell j : i.getCells())
				if(j.getType().equals(ValueType.TEXT) && !dimensionMap.containsKey(((TextValue)j.getValue()).getValue()))
					dimensionMap.put(((TextValue)j.getValue()).getValue(), index++);
	}
	public int getId(String dimensionValue) {
		Integer id = dataHelper.getDimensionValueMap().get(dimensionValue);
		if(id != null)
			return id;
		else
			return -1;
	}
	public String getDimensionValue(int id) {
		String dimension = dataHelper.getIdToDimensionValueMap().get(id);
		if(dimension != null)
			return dimension;
		else
			throw new IllegalArgumentException("Dimension for " + id + " not found");
	}
	public OpenBitSet getBitSet(String[] dimensions) {
		OpenBitSet OpenBitSet = new OpenBitSet();
		for(String i : dimensions) {
			Integer id = dataHelper.getDimensionValueMap().get(i);
			if(id != null)
				OpenBitSet.set(id);
			else
				throw new IllegalArgumentException("Dimension " + i + " not found");
		}
		return OpenBitSet;
	}
	public Set<Integer> getDimensionValueIds() {
		return dataHelper.getIdToDimensionValueMap().keySet();
	}
}