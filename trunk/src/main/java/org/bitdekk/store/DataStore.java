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

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

import org.bitdekk.util.OpenBitSet;

public class DataStore {
	
	private HashMap<String, HashMap<DimensionValue, OpenBitSet>> dimensionBitSet = new HashMap<String, HashMap<DimensionValue, OpenBitSet>>();
	private HashMap<String, MeasureList> measureListMap = new HashMap<String, MeasureList>();
	private HashMap<String, ArrayList<DimensionValue>> dimensionValueMap = new HashMap<String, ArrayList<DimensionValue>>();
	private HashMap<String, HashSet<Measure>> measureMap = new HashMap<String, HashSet<Measure>>();
	
	public HashMap<String, HashMap<DimensionValue, OpenBitSet>> getDimensionBitSet() {
		return dimensionBitSet;
	}
	public void setDimensionBitSet(HashMap<String, HashMap<DimensionValue, OpenBitSet>> dimensionBitSet) {
		this.dimensionBitSet = dimensionBitSet;
	}
	public HashMap<String, MeasureList> getMeasureListMap() {
		return measureListMap;
	}
	public void setMeasureListMap(HashMap<String, MeasureList> measureListMap) {
		this.measureListMap = measureListMap;
	}
	public HashMap<String, ArrayList<DimensionValue>> getDimensionValueMap() {
		return dimensionValueMap;
	}
	public void setDimensionValueMap(HashMap<String, ArrayList<DimensionValue>> dimensionValueMap) {
		this.dimensionValueMap = dimensionValueMap;
	}
	public HashMap<String, HashSet<Measure>> getMeasureMap() {
		return measureMap;
	}
	public void setMeasureMap(HashMap<String, HashSet<Measure>> measureMap) {
		this.measureMap = measureMap;
	}
}
