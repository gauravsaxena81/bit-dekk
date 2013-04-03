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
package org.bitdekk.scenario.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bitdekk.api.IBitSet;
import org.bitdekk.scenario.model.ScenarioRowQuery;

public class ScenarioDataHelper {
	private HashMap<Integer, HashMap<IBitSet, ArrayList<ScenarioRowQuery>>> scenarioRules = new HashMap<Integer, HashMap<IBitSet, ArrayList<ScenarioRowQuery>>>();
	private HashMap<String, List<Integer>> dimensionToDimensionValueIdMap;
	
	public void associateRule(int id, IBitSet keyBitSet, IBitSet ruleBitSet, double[] factor) {
		HashMap<IBitSet, ArrayList<ScenarioRowQuery>> rules = scenarioRules.get(id);
		if(rules == null) {
			rules = new HashMap<IBitSet, ArrayList<ScenarioRowQuery>>();
			scenarioRules.put(id, rules);
		}
		ArrayList<ScenarioRowQuery> ruleList = rules.get(keyBitSet);
		if(ruleList == null) {
			ruleList = new ArrayList<ScenarioRowQuery>();
			rules.put(keyBitSet, ruleList);
		}
		ruleList.add(new ScenarioRowQuery(ruleBitSet, factor));
	}
	public Set<Integer> getScenrios() {
		return scenarioRules.keySet();
	}
	public void setDimensionToDimensionValueIdMap(HashMap<String, List<Integer>> dimensionToDimensionValueIdMap) {
		this.dimensionToDimensionValueIdMap = dimensionToDimensionValueIdMap;
	}
	public HashMap<String, List<Integer>> getDimensionToDimensionValueIdMap() {
		return dimensionToDimensionValueIdMap;
	}
	public ArrayList<ScenarioRowQuery> getScenarioValueQueries(Integer id) {
		ArrayList<ScenarioRowQuery> arrayList = new ArrayList<>();
		for(ArrayList<ScenarioRowQuery> i : scenarioRules.get(id).values())
			arrayList.addAll(i);
		return arrayList;
	}
	public void associateRule(int id, IBitSet key, ArrayList<ScenarioRowQuery> listOfRealRowQueries, double[] factor) {
		for(ScenarioRowQuery i : listOfRealRowQueries)
			for(int j = 0; j < i.getFactor().length; j++)
				i.getFactor()[j] = i.getFactor()[j] * factor[j];
		scenarioRules.get(id).put(key, listOfRealRowQueries);		
	}
	public Set<IBitSet> getScenarioKeyQueries(Integer id) {
		return scenarioRules.get(id).keySet();
	}
	public HashMap<IBitSet, ArrayList<ScenarioRowQuery>> getQueryMap(Integer id) {
		return scenarioRules.get(id);
	}
	public boolean deleteRule(int id, IBitSet key) {
		return scenarioRules.get(id).remove(key) != null;
	}
}
