package org.bitdekk.store;

import org.bitdekk.util.OpenBitSet;

public class Measure {
	
	private String name;
	private double[] valueList;
	private OpenBitSet[] rowIndex;
	
	private int initialSize = 101;
	
	public Measure(){
		valueList = new double[initialSize];
		rowIndex = new OpenBitSet[initialSize];
	}
	
	public Measure(int size){
		this.initialSize = size;
		valueList = new double[initialSize];
		rowIndex = new OpenBitSet[initialSize];
		
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
