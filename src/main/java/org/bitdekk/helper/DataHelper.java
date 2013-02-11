package org.bitdekk.helper;

import java.util.HashMap;

import org.bitdekk.model.Table;
import org.springframework.stereotype.Component;

@Component
public class DataHelper {
	private HashMap<String, Integer> dimensionValueMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> IdToDimensionMap = new HashMap<Integer, String>();
	private HashMap<String, Table> tableMap = new HashMap<String, Table>();
	
	public HashMap<String, Table> getTableMap() {
		return tableMap;
	}
	public HashMap<String, Integer> getDimensionMap() {
		return dimensionValueMap;
	}
	public void setDimensionValueMap(HashMap<String, Integer> dimensionValueMap) {
		this.dimensionValueMap = dimensionValueMap;
	}
	public void setIdToDimensionMap(HashMap<Integer, String> IdToDimensionMap) {
		this.IdToDimensionMap = IdToDimensionMap;
	}
	public HashMap<Integer, String> getIdToDimensionMap() {
		return IdToDimensionMap;
	}
}