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
