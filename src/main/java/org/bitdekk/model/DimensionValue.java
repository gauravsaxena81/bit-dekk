package org.bitdekk.model;

/**
 * 
 * Dimenson Value qualified by dimension
 * @author gaurav.saxena
 *
 */
public class DimensionValue {
	private String dimension;
	private String dimensionValue;
	public DimensionValue(String dimension, String dimensionValue) {
		this.dimension = dimension;
		this.dimensionValue = dimensionValue;
	}
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
	@Override
	public int hashCode() {
		return dimension.hashCode() * 31 + dimensionValue.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof DimensionValue)
			return ((DimensionValue) o).dimensionValue.equals(this.dimensionValue) && ((DimensionValue) o).dimension.equals(this.dimension);
		else
			return false;
	}
}
