package org.bitdekk.helper.sql.grammar;

import java.util.ArrayList;

public class Measure implements IColumn{
	private ArrayList<Object> measureExpressionTokens;
	private String label;
	private String measureExpression;
	public Measure(String measureExpression/*ArrayList<Object> measureExpressionTokens*/, String label) {
		this.measureExpression = measureExpression;
		this.label = label;
	}
	public ArrayList<Object> getMeasureExpressionTokens() {
		return measureExpressionTokens;
	}
	@Override
	public String getLabel() {
		return label;
	}
	public String getMeasureExpression() {
		return measureExpression;
	}
}