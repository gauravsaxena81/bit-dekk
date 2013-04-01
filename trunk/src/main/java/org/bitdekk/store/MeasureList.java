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
