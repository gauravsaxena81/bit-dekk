package org.bitdekk.store;

import org.bitdekk.util.OpenBitSet;

public class MeasureValueList {
	
	private double[] values;
	private OpenBitSet rowBits;
	
	public MeasureValueList(){}
	
	public MeasureValueList(double[] values, OpenBitSet rowBits){
		this.values = values;
		this.rowBits = rowBits;
	}
	
	public double[] getValues(){
		return values;
	}
	
	public void setValues(double[] values){
		this.values = values;
	}
	
	public OpenBitSet getRowBits(){
		return rowBits;
	}
	
	public void setRowBits(OpenBitSet rowBits){
		this.rowBits = rowBits;
	}
}
