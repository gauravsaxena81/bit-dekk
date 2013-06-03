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
package org.bitdekk.distributed.scenario.helper;

import java.util.List;

import org.bitdekk.api.Processor;
import org.bitdekk.distributed.scenario.server.model.CreateDimensionValueRequest;
import org.bitdekk.distributed.scenario.server.model.DeleteDimensionValueRequest;
import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.helper.DataHelper;
import org.bitdekk.scenario.helper.DimensionHelper;
import org.bitdekk.scenario.helper.ScenarioDataHelper;
import org.bitdekk.util.BitDekkUtil;

public class DistributedScenarioDimensionValueHelper {
	private DataHelper dataHelper;
	private ScenarioDataHelper scenarioDataHelper;
	private DimensionHelper dimensionHelper;
	private long timeout = Long.MAX_VALUE;
	
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
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
	public int createDimensionValue(String dimension, String dimensionValue) {
		String generateDimensionValueString = BitDekkUtil.generateDimensionValueString(dimension, dimensionValue);
		int id = dataHelper.getId();
		dataHelper.addToId();
		dataHelper.getDimensionValueMap().put(generateDimensionValueString, id);
		dataHelper.getIdToDimensionValueMap().put(id, generateDimensionValueString);
		scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
		scenarioDataHelper.getDimensonValueToDimensionMap().put(id, dimension);
		CreateDimensionValueRequest createDimensionValueRequest = new CreateDimensionValueRequest();
		createDimensionValueRequest.setDimensionValue(dimensionValue);
		createDimensionValueRequest.setDimension(dimension);
		final boolean[] success = new boolean[]{true};
		if(!BitDekkDistributedUtil.evaluate(timeout, createDimensionValueRequest, Boolean.class, new Processor<Boolean>() {
			@Override
			public void process(Boolean t) {
				success[0] &= t;
			}
		}) && success[0])
			throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
		return id;
	}
	public boolean deleteDimensionValue(String dimension, String dimensionValue) {
		Integer id = dataHelper.getDimensionValueMap().get(BitDekkUtil.generateDimensionValueString(dimension, dimensionValue));
		if(id != null) {
			boolean remove = dataHelper.getDimensionValueMap().remove(BitDekkUtil.generateDimensionValueString(dimension, dimensionValue)) != null;
			dataHelper.getIdToDimensionValueMap().remove(id);
			scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension).add(id);
			scenarioDataHelper.getDimensonValueToDimensionMap().remove(id);
			final boolean[] success = new boolean[]{true};
			DeleteDimensionValueRequest deleteDimensionValueRequest = new DeleteDimensionValueRequest();
			deleteDimensionValueRequest.setDimensionValue(dimensionValue);
			deleteDimensionValueRequest.setDimension(dimension);
			if(BitDekkDistributedUtil.evaluate(timeout, deleteDimensionValueRequest, Boolean.class, new Processor<Boolean>() {
				@Override
				public void process(Boolean t) {
					success[0] &= t;
				}
			}))
				return remove && success[0];
			else
				throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
		}
		return false;
	}
	public List<Integer> getDimensionValueIds(String dimension) {
		return scenarioDataHelper.getDimensionToDimensionValueIdMap().get(dimension);
	}
}
