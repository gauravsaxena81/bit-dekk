package org.bitdekk.aggregation.impl;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.model.MeasureExpression;

public class SumAggregation implements IAggregation {
	private double value = 0;
	private boolean anyValueFound = false;
	private MeasureExpression me;
	@Override
	public void aggregate(double measureValue) {
		value += measureValue;
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
		value += measureValues[0];
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