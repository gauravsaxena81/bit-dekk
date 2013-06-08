package org.bitdekk.scenario.helper;

import org.bitdekk.helper.DataHelper;

public class ScenarioDimensionHelper {
	private DataHelper dataHelper;
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	public void createDimensionValue(String dimension, int dimensionValueId) {
		dataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(dimensionValueId);
		dataHelper.getDimensonValueToDimensionMap().put(dimensionValueId, dimension);
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue, int id) {
		boolean remove = dataHelper.getDimensionToDimensionValueIdMap().get(dimension).remove(Integer.valueOf(id));
		if(remove) {
			dataHelper.getDimensonValueToDimensionMap().remove(id);
			return true;
		} else
			return false;
	}
}
