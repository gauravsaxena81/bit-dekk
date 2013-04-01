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
