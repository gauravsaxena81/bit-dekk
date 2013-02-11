package org.bitdekk.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MeasureList implements Iterable<MeasureValueList>{
	
	private ArrayList<MeasureValueList> measureList;
	private LinkedHashMap<String, Integer> measureIndices;
	
	public ArrayList<MeasureValueList> getMeasureList() {
		return measureList;
	}
	public void setMeasureList(ArrayList<MeasureValueList> measureList) {
		this.measureList = measureList;
	}
	public LinkedHashMap<String, Integer> getMeasureIndices() {
		return measureIndices;
	}
	public void setMeasureIndices(LinkedHashMap<String, Integer> measureIndices) {
		this.measureIndices = measureIndices;
	}
	@Override
	public Iterator<MeasureValueList> iterator() {	
		return measureList.iterator();
	}
	
	public int getMeasureIndex(String measureName){
		return measureIndices.get(measureName);
	}
}
