package org.bitdekk.scenario.helper;

import java.util.HashMap;
import java.util.List;

import org.bitdekk.api.IBitSet;
import org.bitdekk.util.OpenBitSet;

public class DimensionHelper {
	private ScenarioDataHelper scenarioDataHelper;
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public void initializeDimensions(HashMap<String, List<Integer>> dimensionToDimensionValueIdMap) {
		scenarioDataHelper.setDimensionToDimensionValueIdMap(dimensionToDimensionValueIdMap);
	}
	public IBitSet getDimensionValuesBitSet(String dimension) {
		IBitSet bitSet = new OpenBitSet();
		for(Integer i : scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension))
			bitSet.set(i);
		return bitSet;
	}
}
