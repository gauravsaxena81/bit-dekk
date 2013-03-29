package org.bitdekk.scenario.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bitdekk.api.IBitSet;

public class ScenarioDataHelper {
	private HashMap<Integer, HashMap<IBitSet, ArrayList<IBitSet>>> scenarioRules = new HashMap<Integer, HashMap<IBitSet, ArrayList<IBitSet>>>();
	private HashMap<String, List<Integer>> dimensionToDimensionValueIdMap = new HashMap<String, List<Integer>>();
	
	public void associateRule(int id, IBitSet keyBitSet, IBitSet ruleBitSet) {
		HashMap<IBitSet, ArrayList<IBitSet>> rules = scenarioRules.get(id);
		if(rules == null) {
			rules = new HashMap<IBitSet, ArrayList<IBitSet>>();
			scenarioRules.put(id, rules);
		}
		ArrayList<IBitSet> ruleList = rules.get(keyBitSet);
		if(ruleList == null) {
			ruleList = new ArrayList<IBitSet>();
			rules.put(keyBitSet, ruleList);
		}
		ruleList.add(ruleBitSet);
	}
	public Set<Integer> getScenrios() {
		return scenarioRules.keySet();
	}
	public void setDimensionToDimensionValueIdMap(HashMap<String, List<Integer>> dimensionToDimensionValueIdMap) {
		this.dimensionToDimensionValueIdMap  =dimensionToDimensionValueIdMap;
	}
	public HashMap<String, List<Integer>> getDimensionToDimensionValueIdMap() {
		return dimensionToDimensionValueIdMap;
	}
	public ArrayList<IBitSet> getScenarioValueQueries(Integer id) {
		ArrayList<IBitSet> arrayList = new ArrayList<>();
		for(ArrayList<IBitSet> i : scenarioRules.get(id).values())
			arrayList.addAll(i);
		return arrayList;
	}
	public void associateRule(int id, IBitSet i, ArrayList<IBitSet> listOfRealQueries) {
		scenarioRules.get(id).put(i, listOfRealQueries);		
	}
	public Set<IBitSet> getScenarioKeyQueries(Integer id) {
		return scenarioRules.get(id).keySet();
	}
	public HashMap<IBitSet, ArrayList<IBitSet>> getQueryMap(Integer id) {
		return scenarioRules.get(id);
	}
}
