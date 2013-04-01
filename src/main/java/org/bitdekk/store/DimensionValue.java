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

//Deprecated: Marked for removal
public class DimensionValue {

	private String dimensionValue;
	private String dimensionDisplayValue;// Shift to UI
	private int dimensionValueID;
	public String getDimensionValue() {
		return dimensionValue;
	}
	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}
	public String getDimensionDisplayValue() {
		if(dimensionDisplayValue == null)
			return dimensionValue;
		return dimensionDisplayValue;
	}
	public void setDimensionDisplayValue(String dimensionDisplayValue) {
		this.dimensionDisplayValue = dimensionDisplayValue;
	}
	public int getDimensionValueID() {
		return dimensionValueID;
	}
	public void setDimensionValueID(int dimensionValueID) {
		this.dimensionValueID = dimensionValueID;
	}
}
