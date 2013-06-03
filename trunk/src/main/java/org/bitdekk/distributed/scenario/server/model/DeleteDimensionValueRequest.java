package org.bitdekk.distributed.scenario.server.model;

public class DeleteDimensionValueRequest {
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
}
