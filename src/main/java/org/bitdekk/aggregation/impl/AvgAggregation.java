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
package org.bitdekk.aggregation.impl;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.model.MeasureExpression;

/**
 * Computes average of a list
 * @author gaurav.saxena
 * @deprecated because bit-dekk doesn't support aggregation which use division because it creates problem with distributed implementation.
 * Use SUM(column)/COUNT(column)
 */
public class AvgAggregation implements IAggregation{
	private double value = 0;
	private int number = 0;
	private boolean anyValueFound = false;
	private MeasureExpression me;
	@Override
	public void aggregate(double measureValue) {
		value += measureValue;
		number++;
		anyValueFound = true;
	}
	@Override
	public double getValue() {
		if(anyValueFound)
			return value / number;
		else
			return Double.NaN;
	}
	@Override
	public void aggregate(double[] measureValues) {
		value += measureValues[0];
		number++;
		anyValueFound = true;
	}
	@Override
	public void setMeasureExpression(MeasureExpression me) {
		this.me = me;
	}
	@Override
	public MeasureExpression getMeasureExpression() {
		return me;
	}
}