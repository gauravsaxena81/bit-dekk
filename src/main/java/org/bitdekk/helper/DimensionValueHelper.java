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

import org.bitdekk.api.IBitSet;
import org.bitdekk.model.DimensionValue;
import org.bitdekk.util.BitDekkUtil;

import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;


/**
 * Contains all the methods to do with processing of dimension values
 * @author gaurav.saxena
 *
 */
public class DimensionValueHelper {
	
	private DataHelper dataHelper;
	
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	/*public void initializeDimensionValues(HashMap<String, Integer> dimensionMap) {
		dataHelper.setDimensionValueMap(dimensionMap);
		HashMap<Integer, String> idMap = new HashMap<Integer, String>();
		for(String i : dimensionMap.keySet())
			idMap.put(dimensionMap.get(i), i);
		dataHelper.setIdToDimensionValueMap(idMap);
	}*/
	/**
	 * Iterates over the Text and Date type columns to generate ids for dimension values
	 * @param dataTable
	 */
	public void initializeDimensionValues(DataTable dataTable) {
		HashMap<String, Integer> dimensionMap = dataHelper.getDimensionValueMap();
		for(TableRow i : dataTable.getRows()) {
			for(int j = 0; j < i.getCells().size(); j++) {
				if((i.getCells().get(j).getType().equals(ValueType.TEXT) || i.getCells().get(j).getType().equals(ValueType.DATE))) { 
					String generateDimensionValueString = BitDekkUtil.generateDimensionValueString(dataTable.getColumnDescription(j).getLabel()
							, i.getCells().get(j).getValue().toString());
					if(!dimensionMap.containsKey(generateDimensionValueString)) {
						dimensionMap.put(generateDimensionValueString, dataHelper.getId());
						dataHelper.getIdToDimensionValueMap().put(dataHelper.getId(), generateDimensionValueString);
						dataHelper.addToId();
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param dimensionValue
	 * @return id for dimensionValue if found, -1 otherwise
	 */
	public int getId(String dimension, String dimensionValue) {
		Integer id = dataHelper.getDimensionValueMap().get(BitDekkUtil.generateDimensionValueString(dimension, dimensionValue));
		if(id != null)
			return id;
		else
			throw new IllegalArgumentException("Dimension Value with name " + dimension + "-" + dimensionValue + " not found");
	}
	/**
	 * @param id
	 * @return dimensionValue for the id
	 */
	public DimensionValue getDimensionValue(int id) {
		String string = dataHelper.getIdToDimensionValueMap().get(id);
		return new DimensionValue(string.substring(0, string.indexOf('-')), string.substring(string.indexOf('-') + 1));
	}
	/**
	 * 
	 * @param dimensionValues
	 * @returns bitset with bits set for ids of dimensionValues
	 * @throws IllegalArgumentException if any of the dimensionValue is not found
	 */
	public IBitSet getBitSet(DimensionValue[] dimensionValues) {
		IBitSet bitSet = BitDekkUtil.newBitSet();
		for(DimensionValue i : dimensionValues) {
			Integer id = getId(i.getDimension(), i.getDimensionValue());
			if(id != null)
				bitSet.set(id);
			else
				throw new IllegalArgumentException("Dimension value " + i.getDimension() + "-" + i.getDimensionValue() + " not found");
		}
		return bitSet;
	}
	/**
	 * @return all the dimension value ids
	 */
	public Set<Integer> getDimensionValueIds() {
		return dataHelper.getIdToDimensionValueMap().keySet();
	}
}