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
package org.bitdekk.helper.sql.grammar;

import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.helper.DimensionHelper;
import org.bitdekk.helper.MeasureHelper;
import org.bitdekk.helper.Position;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.util.BitDekkUtil.OPERATORS;

/**
 * Contains methods to do with evaluation of an expression
 * @author gaurav.saxena
 *
 */
public class BucketMeasureEvaluator {
	private MeasureHelper measureHelper;
	private DimensionHelper dimensionHelper;
	public DimensionHelper getDimensionHelper() {
		return dimensionHelper;
	}
	public void setDimensionHelper(DimensionHelper dimensionHelper) {
		this.dimensionHelper = dimensionHelper;
	}
	public MeasureHelper getMeasureHelper() {
		return measureHelper;
	}
	public void setMeasureHelper(MeasureHelper measureHelper) {
		this.measureHelper = measureHelper;
	}
	public double getMeasureExpressionValue(OPERATORS[] operatorArray, Object[] measureExpressionTokens, Position pos, double[] row, HashMap<String, Integer> measureIndexMap) {
		switch(operatorArray[pos.pos]) {
			case PLUS:
				++pos.pos;
				return add(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
			case MINUS:
				++pos.pos;
				return subtract(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
			case MULTIPLY:
				++pos.pos;
				return multiply(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
			case DIVIDE:
				++pos.pos;
				return divide(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
			case NUMBER:
				return (double)measureExpressionTokens[pos.pos];
			case AGGREGATION:
				return aggregate(((IAggregation)measureExpressionTokens[pos.pos]), ((IAggregation)measureExpressionTokens[pos.pos]).getMeasureExpression()
						, measureIndexMap, row);
			case CELL_VALUE:
				return row[measureIndexMap.get(measureExpressionTokens[pos.pos])];
			default:
				throw new IllegalArgumentException("Unidentified token found in grammar");
		}
			
	}
	private double aggregate(IAggregation aggregation, MeasureExpression measureExpression, HashMap<String, Integer> measureIndexMap, double[] row) {
		Position position = new Position();
		//for(double[] i : rows) {
			position.pos = 0;
			aggregation.aggregate(getMeasureExpressionValue(measureExpression.getOperators(), measureExpression.getTokenArray(), position, row, measureIndexMap));
		//}
		return aggregation.getValue();
	}
	private double divide(OPERATORS[] operatorArray, Object[] measureExpressionTokens, Position pos, double[] row, HashMap<String, Integer> measureIndexMap) {
		double v1 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		++pos.pos;
		double v2 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		return v1 / v2;
	}
	private double multiply(OPERATORS[] operatorArray, Object[] measureExpressionTokens, Position pos, double[] row, HashMap<String, Integer> measureIndexMap) {
		double v1 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		++pos.pos;
		double v2 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		return v1 * v2;
	}
	private double subtract(OPERATORS[] operatorArray, Object[] measureExpressionTokens, Position pos, double[] row, HashMap<String, Integer> measureIndexMap) {
		double v1 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		++pos.pos;
		double v2 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		return v1 - v2;
	}
	private double add(OPERATORS[] operatorArray, Object[] measureExpressionTokens, Position pos, double[] row, HashMap<String, Integer> measureIndexMap) {
		double v1 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		++pos.pos;
		double v2 = getMeasureExpressionValue(operatorArray, measureExpressionTokens, pos, row, measureIndexMap);
		return v1 + v2;
	}
	public BitdekkErrorHandlingLexer getLexer(String measureExpression) {
		return new BitdekkErrorHandlingLexer(new ANTLRStringStream(measureExpression));
	}
	public void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException {
		new BitdekkErrorHandlingParser(tokens, gme).stat();
	}
}