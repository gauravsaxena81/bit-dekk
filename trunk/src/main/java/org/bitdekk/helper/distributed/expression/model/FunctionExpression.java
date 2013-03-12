package org.bitdekk.helper.distributed.expression.model;

import org.bitdekk.aggregation.IAggregation;

public class FunctionExpression {
	private String expression;
	private IAggregation aggregation;
	
	public FunctionExpression(IAggregation aggregation, String expression) {
		this.aggregation = aggregation;
		this.expression = expression;
	}
	public IAggregation getAggregation() {
		return aggregation;
	}
	public void setAggregation(IAggregation aggregation) {
		this.aggregation = aggregation;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
