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
