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

import org.bitdekk.model.Table;

public class DataHelper {
	private HashMap<String, Integer> dimensionValueMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> IdToDimensionMap = new HashMap<Integer, String>();
	private HashMap<String, Table> tableMap = new HashMap<String, Table>();
	
	public HashMap<String, Table> getTableMap() {
		return tableMap;
	}
	public HashMap<String, Integer> getDimensionValueMap() {
		return dimensionValueMap;
	}
	public void setDimensionValueMap(HashMap<String, Integer> dimensionValueMap) {
		this.dimensionValueMap = dimensionValueMap;
	}
	public void setIdToDimensionValueMap(HashMap<Integer, String> IdToDimensionMap) {
		this.IdToDimensionMap = IdToDimensionMap;
	}
	public HashMap<Integer, String> getIdToDimensionValueMap() {
		return IdToDimensionMap;
	}
}