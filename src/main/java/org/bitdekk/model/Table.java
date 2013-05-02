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