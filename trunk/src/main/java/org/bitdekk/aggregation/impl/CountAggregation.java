package org.bitdekk.aggregation.impl;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.model.MeasureExpression;

public class CountAggregation implements IAggregation{
	private int number = 0;
	private boolean anyValueFound = false;
	private MeasureExpression me;
	@Override
	public void aggregate(double measureValue) {
		number++;
		anyValueFound = true;
	}
	@Override
	public double getValue() {
		if(anyValueFound)
			return number;
		else
			return Double.NaN;
	}
	@Override
	public void aggregate(double[] measureValues) {
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