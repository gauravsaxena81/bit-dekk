package org.bitdekk.distributed.scenario.server.model;

public class CreateDimensionValueRequest {
	private String dimension;
	private String dimensionValue;
	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}