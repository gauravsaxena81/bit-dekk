package org.bitdekk.helper;

import java.util.HashMap;
import java.util.Set;

import org.bitdekk.util.OpenBitSet;

import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.TextValue;
import com.google.visualization.datasource.datatable.value.ValueType;


public class DimensionHelper {
	
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
		dataHelper.setIdToDimensionMap(idMap);
	}
	public void initialize(DataTable dataTable) {
		HashMap<String, Integer> dimensionMap = dataHelper.getDimensionMap();
		int index = 0;
		for(TableRow i : dataTable.getRows())
			for(TableCell j : i.getCells())
				if(j.getType().equals(ValueType.TEXT) && !dimensionMap.containsKey(((TextValue)j.getValue()).getValue()))
					dimensionMap.put(((TextValue)j.getValue()).getValue(), index++);
	}
	public int getId(String dimensionValue) {
		return dataHelper.getDimensionMap().get(dimensionValue);
	}
	public String getDimensionValue(int id) {
		String dimension = dataHelper.getIdToDimensionMap().get(id);
		if(dimension != null)
			return dimension;
		else
			throw new IllegalArgumentException("Dimension for " + id + " not found");
	}
	public OpenBitSet getBitSet(String[] dimensions) {
		OpenBitSet OpenBitSet = new OpenBitSet();
		for(String i : dimensions) {
			Integer id = dataHelper.getDimensionMap().get(i);
			if(id != null)
				OpenBitSet.set(id);
			else
				throw new IllegalArgumentException("Dimension " + i + " not found");
		}
		return OpenBitSet;
	}
	public Set<Integer> getDimensionIds() {
		return dataHelper.getIdToDimensionMap().keySet();
	}
}