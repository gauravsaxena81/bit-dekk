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

import java.util.Set;

import org.bitdekk.api.IBitSet;
import org.bitdekk.api.Processor;
import org.bitdekk.distributed.scenario.server.model.DeleteRuleRequest;
import org.bitdekk.distributed.server.model.AssociateRuleRequest;
import org.bitdekk.distributed.util.BitDekkDistributedUtil;
import org.bitdekk.helper.DataHelper;
import org.bitdekk.helper.DimensionHelper;
import org.bitdekk.scenario.ScenarioUtil;
import org.bitdekk.scenario.helper.ScenarioDataHelper;
import org.bitdekk.scenario.helper.ScenarioDimensionValueHelper;

public class DistributedScenarioHelper {
	private ScenarioDataHelper scenarioDataHelper;
	private DataHelper dataHelper;
	private DimensionHelper dimensionHelper;
	private ScenarioDimensionValueHelper scenarioDimensionValueHelper;
	public DataHelper getDataHelper() {
		return dataHelper;
	}
	public void setDataHelper(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	public ScenarioDimensionValueHelper getScenarioDimensionValueHelper() {
		return scenarioDimensionValueHelper;
	}
	public void setScenarioDimensionValueHelper(
			ScenarioDimensionValueHelper scenarioDimensionValueHelper) {
		this.scenarioDimensionValueHelper = scenarioDimensionValueHelper;
	}
	private long timeout = Long.MAX_VALUE;
	
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public ScenarioDataHelper getScenarioDataHelper() {
		return scenarioDataHelper;
	}
	public void setScenarioDataHelper(ScenarioDataHelper scenarioDataHelper) {
		this.scenarioDataHelper = scenarioDataHelper;
	}
	public void associateRule(String dimension, String scenarioDimensionValue, IBitSet ruleBitSet, double[] factor) {
		Set<Integer> scenarios = ScenarioUtil.pi(ruleBitSet, scenarioDataHelper);
		int id = scenarioDimensionValueHelper.getId(dimension, scenarioDimensionValue);
		if(!scenarios.isEmpty()) {
			for(IBitSet i : ScenarioUtil.neeta(scenarios, ruleBitSet, dataHelper, dimensionHelper)) {
				IBitSet key = i.clone();
				key.set(id);
				scenarioDataHelper.associateRule(id, key
					, ScenarioUtil.mu(ScenarioUtil.theta(ScenarioUtil.pi(i, scenarioDataHelper), ruleBitSet, scenarioDataHelper, dataHelper, dimensionHelper), dimensionHelper, dataHelper)
					, factor);
			}
		} else {
			IBitSet key = ruleBitSet.clone();
			key.set(id);
			scenarioDataHelper.associateRule(id, key, ruleBitSet, factor);
		}
		AssociateRuleRequest associateRuleRequest = new AssociateRuleRequest();
		associateRuleRequest.setRuleBitSet(ruleBitSet);
		associateRuleRequest.setFactor(factor);
		associateRuleRequest.setDimension(dimension);
		associateRuleRequest.setScenarioDimensionValue(scenarioDimensionValue);
		final boolean[] success = new boolean[]{true};
		if(!BitDekkDistributedUtil.evaluate(timeout, associateRuleRequest, Boolean.class, new Processor<Boolean>() {
			@Override
			public void process(Boolean t) {
				success[0] &= t;
			}
		}) && success[0])
			throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
	}
	public boolean deleteRule(String dimension, String dimensionValue, int id, IBitSet ruleBitSet) {
		IBitSet clone = ruleBitSet.clone();
		clone.set(id);
		boolean deleteRule = scenarioDataHelper.deleteRule(id, clone);
		DeleteRuleRequest deleteRuleRequest = new DeleteRuleRequest();
		deleteRuleRequest.setDimension(dimension);
		deleteRuleRequest.setDimensionValue(dimensionValue);
		deleteRuleRequest.setKey(clone);
		final boolean[] success = new boolean[]{true};
		if(BitDekkDistributedUtil.evaluate(timeout, deleteRuleRequest, Boolean.class, new Processor<Boolean>() {
			@Override
			public void process(Boolean t) {
				success[0] &= t;
			}
		}))
			return deleteRule & success[0];
		else
			throw new RuntimeException("Timeout occured while waiting for response. One or more nodes may be down.");
	}
}
