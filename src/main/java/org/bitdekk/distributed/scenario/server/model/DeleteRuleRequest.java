package org.bitdekk.distributed.scenario.server.model;

import org.bitdekk.api.IBitSet;

public class DeleteRuleRequest {
	private IBitSet key;
	private String dimension;
	private String dimensionValue;
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getDimensionValue() {
		return dimensionValue;
	}
	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}
	public IBitSet getKey() {
		return key;
	}
	public void setKey(IBitSet key) {
		this.key = key;
	}
}
