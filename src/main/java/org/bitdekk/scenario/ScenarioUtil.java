package org.bitdekk.scenario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bitdekk.api.IBitSet;
import org.bitdekk.scenario.helper.DimensionHelper;
import org.bitdekk.scenario.helper.ScenarioDataHelper;

public class ScenarioUtil {
	public static ArrayList<IBitSet> mu(ArrayList<ArrayList<IBitSet>> listsOfQueries, DimensionHelper dimensionHelper, ScenarioDataHelper scenarioDataHelper) {
		ArrayList<IBitSet> finalList = new ArrayList<IBitSet>();
		for(ArrayList<IBitSet> i : listsOfQueries) {
			for(IBitSet j : i) {
				for(int k = 0; k < finalList.size(); ) {
					if(finalList.get(k) != null) {
						finalList.get(k).and(j);
						if(!containsAllDimensions(finalList.get(k), dimensionHelper, scenarioDataHelper))
							finalList.set(k, null);
						else
							k++;
					}
				}
			}
		}
		return nonNullQueries(finalList);
	}
	public static ArrayList<IBitSet> nonNullQueries(ArrayList<IBitSet> nullQueryList) {
		ArrayList<IBitSet> nonNullQueryList = new ArrayList<IBitSet>(nullQueryList.size());
		for(IBitSet i : nullQueryList)
			if(i != null)
				nonNullQueryList.add(i);
		return nonNullQueryList;
	}
	public static boolean containsAllDimensions(IBitSet query, DimensionHelper dimensionHelper, ScenarioDataHelper scenarioDataHelper) {
		for(String i : scenarioDataHelper.getDimensionToDimensionValueIdMap().keySet())
			if(!dimensionHelper.getDimensionValuesBitSet(i).intersects(query))
				return false;
		return true;
	}
	public static ArrayList<ArrayList<IBitSet>> theta(Set<Integer> scenarios, ScenarioDataHelper scenarioDataHelper) {
		ArrayList<ArrayList<IBitSet>> listsOfQueries = new ArrayList<ArrayList<IBitSet>>();
		for(Integer i : scenarios)
			listsOfQueries.add(scenarioDataHelper.getScenarioValueQueries(i));
		return listsOfQueries;
	}
	public static Set<Integer> pi(IBitSet query, ScenarioDataHelper scenarioDataHelper) {
		Set<Integer> set = new HashSet<Integer>();
		for(Integer i : scenarioDataHelper.getScenrios())
			if(query.get(i))
				set.add(i);
		return set;
	}
	public static ArrayList<IBitSet> neeta(Set<Integer> scenarios, IBitSet query, ScenarioDataHelper scenarioDataHelper, DimensionHelper dimensionHelper) {
		ArrayList<IBitSet> linkedList = new ArrayList<IBitSet>();
		ArrayList<IBitSet> tempList = new ArrayList<IBitSet>();
		IBitSet scenarioTrimmedQuery = trimScenarios(scenarios, query);
		linkedList.add(scenarioTrimmedQuery);
		for(String i : scenarioDataHelper.getDimensionToDimensionValueIdMap().keySet()) {
			IBitSet dimensionValuesBitSet = dimensionHelper.getDimensionValuesBitSet(i);
			dimensionValuesBitSet.and(query);
			if(containsScenario(scenarios, dimensionValuesBitSet)) {
				if(scenarioTrimmedQuery.intersects(dimensionValuesBitSet))
					tempList.addAll(linkedList);
				for(int j = dimensionValuesBitSet.nextSetBit(0); j > -1; j = dimensionValuesBitSet.nextSetBit(j + 1)) {
					for(IBitSet k : linkedList) {
						IBitSet clone = k.clone();
						tempList.add(clone);
						clone.set(j);
					}
				}
				linkedList.clear();
				linkedList.addAll(tempList);
				tempList.clear();
			}
		}
		return linkedList;
	}
	public static IBitSet trimScenarios(Set<Integer> scenarios, IBitSet query) {
		IBitSet clone = query.clone();
		for(Integer i : scenarios)
			clone.clear(i);
		return clone;
	}
	public static boolean containsScenario(Set<Integer> scenarios, IBitSet dimensionValuesBitSet) {
		for(Integer i : scenarios)
			if(dimensionValuesBitSet.get(i))
				return true;
		return false;
	}
}
