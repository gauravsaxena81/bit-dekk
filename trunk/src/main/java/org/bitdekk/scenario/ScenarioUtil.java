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
package org.bitdekk.scenario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bitdekk.api.IBitSet;
import org.bitdekk.scenario.helper.DimensionHelper;
import org.bitdekk.scenario.helper.ScenarioDataHelper;
import org.bitdekk.scenario.model.ScenarioRowQuery;

public class ScenarioUtil {
	/**
	 * Intersect various lists of scenario queries and select those queries which have all the dimension values present
	 * @param scenarioRowQueriesList
	 * @param dimensionHelper
	 * @param scenarioDataHelper
	 * @return
	 */
	public static ArrayList<ScenarioRowQuery> mu(ArrayList<ArrayList<ScenarioRowQuery>> scenarioRowQueriesLists, DimensionHelper dimensionHelper
			, ScenarioDataHelper scenarioDataHelper) {
		ArrayList<ScenarioRowQuery> currentList = new ArrayList<ScenarioRowQuery>();
		ArrayList<ScenarioRowQuery> tempList = new ArrayList<ScenarioRowQuery>();
		for(ArrayList<ScenarioRowQuery> i : scenarioRowQueriesLists) {
			if(currentList.isEmpty()) {
				for(ScenarioRowQuery k : i)
					currentList.add(k.clone());
			} else {
				for(ScenarioRowQuery j : i) {
					for(ScenarioRowQuery k : currentList) {
						ScenarioRowQuery clone = k.clone();
						clone.getQuery().and(j.getQuery());
						for(int l = 0; l < clone.getFactor().length; l++)
							clone.getFactor()[l] = clone.getFactor()[l] * j.getFactor()[l];
						if(containsAllDimensions(clone.getQuery(), dimensionHelper, scenarioDataHelper))
							tempList.add(clone);
					}
				}
				currentList.clear();
				currentList.addAll(tempList);
				tempList.clear();
			}
		}
		return currentList;
	}
	public static boolean containsAllDimensions(IBitSet query, DimensionHelper dimensionHelper, ScenarioDataHelper scenarioDataHelper) {
		for(String i : scenarioDataHelper.getDimensionToDimensionValueIdMap().keySet())
			if(!dimensionHelper.getDimensionValuesBitSet(i).intersects(query))
				return false;
		return true;
	}
	/**
	 * Given a set of scenarios get a list of list of queries. They can be found in the value part of the scenarioRules
	 * @param scenarios
	 * @param ruleBitSet 
	 * @param scenarioDataHelper
	 * @param dimensionHelper 
	 * @return
	 */
	public static ArrayList<ArrayList<ScenarioRowQuery>> theta(Set<Integer> scenarios, IBitSet ruleBitSet, ScenarioDataHelper scenarioDataHelper
			, DimensionHelper dimensionHelper) {
		ArrayList<ArrayList<ScenarioRowQuery>> scenarioRowQueriesLists = new ArrayList<ArrayList<ScenarioRowQuery>>();
		for(Integer i : scenarios) {
			for(IBitSet j : scenarioDataHelper.getScenarioRules(i).keySet()) {
				IBitSet clone = j.clone();
				clone.and(ruleBitSet);
				if(containsAllDimensions(clone, dimensionHelper, scenarioDataHelper))
					scenarioRowQueriesLists.add(scenarioDataHelper.getScenarioRules(i).get(j));
			}
		}
		return scenarioRowQueriesLists;
	}
	public static Set<Integer> pi(IBitSet query, ScenarioDataHelper scenarioDataHelper) {
		Set<Integer> set = new HashSet<Integer>();
		for(Integer i : scenarioDataHelper.getScenrios())
			if(query.get(i))
				set.add(i);
		return set;
	}
	/**
	 * Create combination of query such that each combination has at most 1 scenario dimension value for each dimension. Combination doesn't need to be made for real
	 * dimension values.
	 * @param scenarios
	 * @param query
	 * @param scenarioDataHelper
	 * @param dimensionHelper
	 * @return
	 */
	public static ArrayList<IBitSet> neeta(Set<Integer> scenarios, IBitSet query, ScenarioDataHelper scenarioDataHelper, DimensionHelper dimensionHelper) {
		ArrayList<IBitSet> list = new ArrayList<IBitSet>();
		ArrayList<IBitSet> tempList = new ArrayList<IBitSet>();
		IBitSet scenarioTrimmedQuery = trimScenarios(scenarios, query);
		list.add(scenarioTrimmedQuery);
		for(String i : scenarioDataHelper.getDimensionToDimensionValueIdMap().keySet()) {
			IBitSet dimensionValuesBitSet = dimensionHelper.getDimensionValuesBitSet(i);
			dimensionValuesBitSet.and(query);
			if(containsScenario(scenarios, dimensionValuesBitSet)) {
				//I need all the queries which have other real dimension values for a dimension as it is. 
				//In case the query has only scenario dimension values for a dimension, the list is of no use. 
				//The list with combination of these scenarios will be made later below
				if(scenarioTrimmedQuery.intersects(dimensionValuesBitSet))
					tempList.addAll(list);
				//combination creating tree. Pick root and create children which have a particular scenario dimension value set. Repeat with the next dimension
				for(int j = dimensionValuesBitSet.nextSetBit(0); j > -1; j = dimensionValuesBitSet.nextSetBit(j + 1)) {
					for(IBitSet k : list) {
						IBitSet clone = k.clone();
						tempList.add(clone);
						clone.set(j);
					}
				}
				list.clear();
				list.addAll(tempList);
				tempList.clear();
			}
		}
		return list;
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
