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
package org.bitdekk.distributed.expression.model;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.aggregation.impl.CountAggregation;
import org.bitdekk.aggregation.impl.SumAggregation;

public class FunctionExpression {
	private String expression;
	private IAggregation aggregation;
	
	public FunctionExpression(IAggregation aggregation, String expression) {
		setAggregation(aggregation);
		this.expression = expression;
	}
	public IAggregation getAggregation() {
		return aggregation;
	}
	public void setAggregation(IAggregation aggregation) {
		//In case of distributed systems, Count received from nodes need to be summed to get the final count
		if(aggregation instanceof CountAggregation)
			this.aggregation = new SumAggregation();
		else
			this.aggregation = aggregation;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
