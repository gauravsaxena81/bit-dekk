package org.bitdekk.aggregation.impl;

import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.expression.MeasureExpression;

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
	@Override
	public void initialize() {
		value = 0;
		number = 0;
		anyValueFound = false;
	}
}
