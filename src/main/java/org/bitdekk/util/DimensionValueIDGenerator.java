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
package org.bitdekk.util;

import java.util.HashMap;
import org.bitdekk.store.Dimension;
import java.util.concurrent.atomic.AtomicInteger;

//Deprecated
/* In future maybe this will be persisted. But since everything is in memory right now, everything goes
 * down when the system crashes or when main exits.
 */
public class DimensionValueIDGenerator{
	
	//Remove the following maintain a reference for dimension dictionary
	HashMap<Dimension,HashMap<String,Integer>> dimensionSpace;
	HashMap<Dimension,AtomicInteger> dimensionIDSequence;
	
	public DimensionValueIDGenerator(){
		dimensionSpace = new HashMap<Dimension,HashMap<String,Integer>>();
		dimensionIDSequence = new HashMap<Dimension, AtomicInteger>();
	}
	
	/*
	 * crappy code to be refactored
	 */
	
	public int getID(Dimension dim, String dimValue){
		//id already exists for the dimension value
		if(dimensionSpace.containsKey(dim)&& dimensionSpace.get(dim).containsKey(dimValue)){
			return dimensionSpace.get(dim).get(dimValue);
		}
		
		// dimension exists but dimension value does not exist
		if(dimensionSpace.containsKey(dim) && !dimensionSpace.get(dim).containsKey(dimValue)){
			// get a new ID from the sequence
			// since dimensionSpace has the dimension it should also be present in the Sequence
			int i = dimensionIDSequence.get(dim).addAndGet(1);
			// add above to the dimension space
			dimensionSpace.get(dim).put(dimValue, i);
			return i;
		}
		
		if(!dimensionSpace.containsKey(dim)){
			assert !dimensionIDSequence.containsKey(dim);
			dimensionIDSequence.put(dim, new AtomicInteger(0));
			int i = dimensionIDSequence.get(dim).addAndGet(1);
			HashMap<String,Integer> t = new HashMap<String, Integer>();
			t.put(dimValue, i);
			dimensionSpace.put(dim, t);
			return i;
		}
		
		System.out.println("If you are getting here something is wrong");

		return 0;
	}
}
