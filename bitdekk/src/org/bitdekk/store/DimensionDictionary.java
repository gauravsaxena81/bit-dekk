package org.bitdekk.store;


import java.util.HashMap;
import com.google.common.collect.HashBiMap;

//Primarily store Dimensions, their ID and look up
public class DimensionDictionary {
	
	private static final int BIMAPSIZE = 16;
	
	public HashMap<String, HashBiMap<Integer,String>> dimensionDictionary; 
	
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
		dimensionDictionary = new HashMap<>();
	}
	
	public void addDimension(String dimName){
		if(!dimensionDictionary.containsKey(dimName)){
			HashBiMap<Integer,String> dimValues = HashBiMap.create(BIMAPSIZE); 
			dimensionDictionary.put(dimName, dimValues);
		}else{
			
			//Do Nothing. Already there
		}
	}
	
	public void addDimValue(String dimName, int dimValID, String dimValue ){
		if(!dimensionDictionary.containsKey(dimName)){
			HashBiMap<Integer,String> dimValues = HashBiMap.create(BIMAPSIZE); 
			dimValues.put(dimValID, dimValue);
			dimensionDictionary.put(dimName, dimValues);
		}else{
			dimensionDictionary.get(dimName).put(dimValID, dimValue);
		}
	}
	
	public int getDimValID(String dimName, String dimValue){
		//why inverse, can't we just have getKey()?
		return dimensionDictionary.get(dimName).inverse().get(dimValue);
	}
	
	public String getDimVal(String dimName, int dimValID){
		return dimensionDictionary.get(dimName).get(dimValID);
	}
}
