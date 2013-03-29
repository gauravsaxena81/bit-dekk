package org.bitdekk.scenario.helper;

import org.bitdekk.helper.DataHelper;

public class ScenarioDimensionValueHelper {
	private DataHelper dataHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}

	public void addDimensionValue(String dimension, String dimensionValue, int id) {
		dataHelper.getDimensionValueMap().put(dimensionValue, id);
		dataHelper.getIdToDimensionValueMap().put(id, dimensionValue);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
	}
	public void deleteDimensionValue(String dimension, String dimensionValue, int id) {
		dataHelper.getDimensionValueMap().remove(dimensionValue);
		dataHelper.getIdToDimensionValueMap().remove(id);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
	}
}
