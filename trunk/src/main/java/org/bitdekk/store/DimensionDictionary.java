package org.bitdekk.store;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.HashBiMap;

//Primarily store Dimensions, their ID and look up
public class DimensionDictionary {
	
	private static final int BIMAPSIZE = 16;
	
	public HashMap<String, HashBiMap<Integer,String>> dimensionDictionary;
	AtomicInteger dimensionIDSequence;
	//public HashMap<String, AtomicInteger> dimensionIDSequence;
	
	//Use a bidirectional map for Dimension Dictionary
	//Should we add the mechanism for generating IDs right here?
	//Dimensions are strings, what about date time?
	//Dimension value is supposed to be accessed in context of dimension only
	//There is no guarantee that a dimension value will be unique in a global context
	//Do we need to define table spaces also - is not a bad idea 
	//but if in doubt - LEAVE IT OUT
	//Discard Dimension and DimensionValue - unnecessary object churn.
	//Figure out a method for generating bit set.
	
	public DimensionDictionary(){
		dimensionDictionary = new HashMap<String, HashBiMap<Integer, String>>();
		dimensionIDSequence = new AtomicInteger(0);
	}
	
	public void addDimension(String dimName){
		if(!dimensionDictionary.containsKey(dimName)){
			HashBiMap<Integer,String> dimValues = HashBiMap.create(BIMAPSIZE); 
			dimensionDictionary.put(dimName, dimValues);
		}
	}
	
	public void addDimensionValue(String dimName, String dimValue ){
		int dimValID;
		if(!dictHasDimension(dimName)){//Dictionary has no dimension and dimension value
			addDimension(dimName);
			dimValID = dimensionIDSequence.addAndGet(1);
			dimensionDictionary.get(dimName).put(dimValID, dimValue);		
		}else if(getDimensionValueID(dimName, dimValue) == -1){//Dictionary has dimension but no dimension value
			dimValID = dimensionIDSequence.addAndGet(1);
			dimensionDictionary.get(dimName).put(dimValID, dimValue);
		}else{//Dictionary has dimension and dimension value
			//do nothing
		}
	}
	
	public int getDimensionValueID(String dimName, String dimValue){
		//Valid dimension value ID can be positive only
		if(dimensionDictionary.get(dimName).inverse().containsKey(dimValue)){
			return dimensionDictionary.get(dimName).inverse().get(dimValue);
		}else{
			return -1;
		}
	}
	
	public String getDimensionValue(String dimName, int dimValID){
		return dimensionDictionary.get(dimName).get(dimValID);
	}
	
	public boolean dictHasDimension(String dimName){
		if(dimensionDictionary.containsKey(dimName)){
			return dimensionDictionary.containsKey(dimName);
		}else{
			return false;
		}
	}
	
	public Set<String> getDimensions(){
		return dimensionDictionary.keySet();
	}
	
	public String toString(){
		StringBuilder strB = new StringBuilder();
		strB.append("Dimension Dictionary:\n");
		
		Iterator<String> dictIter = dimensionDictionary.keySet().iterator();
		
		while(dictIter.hasNext()){
			String key = dictIter.next();
			strB.append("Dimension: " + key + "\n");
			Map<Integer,String> dimValMap = dimensionDictionary.get(key);
			Iterator<Integer> valIter = dimValMap.keySet().iterator();
			
			while(valIter.hasNext()){
				int valID = valIter.next();
				strB.append("Dimension Value ID: " + valID + " ; Dimension Value: " + dimValMap.get(valID) + "\n");
			}
			strB.append("\n");
		}
		return strB.toString();
	}
}
