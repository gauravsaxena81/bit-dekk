package org.bitdekk.aggregation.impl;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.MeasureExpression;

public class MinAggregation implements IAggregation {
	private double value = Double.MAX_VALUE;
	private boolean anyValueFound = false;
	private MeasureExpression me;
	@Override
	public void aggregate(double measureValue) {
		if(value > measureValue)
			value = measureValue;
		anyValueFound = true;
	}
	@Override
	public double getValue() {
		if(anyValueFound)
			return value ;
		else
			return Double.NaN;
	}
	@Override
	public void aggregate(double[] measureValues) {
		if(value > measureValues[0])
			value = measureValues[0];
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