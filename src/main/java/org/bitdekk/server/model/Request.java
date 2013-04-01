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
package org.bitdekk.server.model;

import org.bitdekk.api.IBitSet;

public class Request {
	private String tableName;
	private IBitSet filterBitSet;
	private IBitSet viewBitSet;
	private String measureExpression;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public IBitSet getFilterBitSet() {
		return filterBitSet;
	}
	public void setFilterBitSet(IBitSet filterBitSet) {
		this.filterBitSet = filterBitSet;
	}
	public IBitSet getViewBitSet() {
		return viewBitSet;
	}
	public void setViewBitSet(IBitSet viewBitSet) {
		this.viewBitSet = viewBitSet;
	}
	public String getMeasureExpression() {
		return measureExpression;
	}
	public void setMeasureExpression(String measureExpression) {
		this.measureExpression = measureExpression;
	}
}
