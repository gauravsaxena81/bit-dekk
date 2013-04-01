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
package org.bitdekk.scenario.model;

import java.util.Arrays;

import org.bitdekk.api.IBitSet;

public class ScenarioRowQuery {
	private IBitSet query;
	private double[] factor;
	public ScenarioRowQuery() {}
	public ScenarioRowQuery(IBitSet query, double[] factor) {
		this.query = query;
		this.factor = factor;
	}
	public IBitSet getQuery() {
		return query;
	}
	public void setQuery(IBitSet query) {
		this.query = query;
	}
	public double[] getFactor() {
		return factor;
	}
	public void setFactor(double[] factor) {
		this.factor = factor;
	}
	public ScenarioRowQuery clone() {
		return new ScenarioRowQuery(this.query.clone(), Arrays.copyOf(this.factor, this.factor.length));
	}
}
