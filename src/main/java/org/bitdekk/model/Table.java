package org.bitdekk.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {
	private ArrayList<DataRow> arrayList = new ArrayList<DataRow>();
	private HashMap<String, Integer> measureIndexMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> dimensionIndexMap = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> getDimensionIndexMap() {
		return dimensionIndexMap;
	}
	public ArrayList<DataRow> getRows() {
		return arrayList;
	}
	public HashMap<String, Integer> getMeasureIndexMap() {
		return measureIndexMap;
	}
}