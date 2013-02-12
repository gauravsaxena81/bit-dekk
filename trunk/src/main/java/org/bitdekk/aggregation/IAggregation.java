package org.bitdekk.aggregation;

import org.bitdekk.helper.expression.MeasureExpression;


public interface IAggregation {
	public void aggregate(double[] measureValues);
	public double getValue();
	public void aggregate(double measureValue);
	public void setMeasureExpression(MeasureExpression me);
	public MeasureExpression getMeasureExpression();
}