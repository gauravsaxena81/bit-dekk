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
package org.bitdekk.helper;

import java.util.ArrayList;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.helper.expression.BitdekkErrorHandlingLexer;
import org.bitdekk.helper.expression.BitdekkErrorHandlingParser;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.helper.expression.model.MeasureExpression;
import org.bitdekk.model.DataRow;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

/**
 * Contains methods to do with evaluation of an expression
 * @author gaurav.saxena
 *
 */
public class EvaluationHelper implements IEvaluation {
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
	@Override
	public double getMeasureExpressionValue(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		if(measureExpressionTokens.get(pos.pos).equals("+")) {
			++pos.pos;
			return add(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		} else if(measureExpressionTokens.get(pos.pos).equals("-")) {
			++pos.pos;
			return subtract(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		} else if(measureExpressionTokens.get(pos.pos).equals("*"))  {
			++pos.pos;
			return multiply(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		} else if(measureExpressionTokens.get(pos.pos).equals("/")) {
			++pos.pos;
			return divide(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		} else if(measureExpressionTokens.get(pos.pos) instanceof Double)
			return (Double)measureExpressionTokens.get(pos.pos);
		else if(measureExpressionTokens.get(pos.pos) instanceof IAggregation)
			return aggregate(((IAggregation)measureExpressionTokens.get(pos.pos)), ((IAggregation)measureExpressionTokens.get(pos.pos)).getMeasureExpression(), tableName
				, unifiedQuery);
		else
			return row.getMeasureValues()[measureHelper.getTable(tableName).getMeasureIndexMap().get(measureExpressionTokens.get(pos.pos))];
	}
	private double aggregate(IAggregation aggregation, MeasureExpression measureExpression, String tableName, IBitSet unifiedQuery) {
		Position position = new Position();
		for(DataRow i : measureHelper.getTable(tableName).getRows()) {
			if(unifiedQuery.contains(i.getMeasureQuery())) {
				position.pos = 0;
				aggregation.aggregate(getMeasureExpressionValue(measureExpression.getTokens(), position, i, tableName, unifiedQuery));
			}			
		}
		return aggregation.getValue();
	}
	private double divide(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		return v1 / v2;
	}
	private double multiply(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		return v1 * v2;
	}
	private double subtract(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		return v1 - v2;
	}
	private double add(ArrayList<Object> measureExpressionTokens, Position pos, DataRow row, String tableName, IBitSet unifiedQuery) {
		double v1 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		++pos.pos;
		double v2 = getMeasureExpressionValue(measureExpressionTokens, pos, row, tableName, unifiedQuery);
		return v1 + v2;
	}
	@Override
	public BitdekkErrorHandlingLexer getLexer(String measureExpression) {
		return new BitdekkErrorHandlingLexer(new ANTLRStringStream(measureExpression));
	}
	@Override
	public void parse(CommonTokenStream tokens, GroupedMeasureExpression gme) throws RecognitionException {
		new BitdekkErrorHandlingParser(tokens, gme).stat();
	}
	@Override
	public DataTable select(String tableName, IBitSet filterBitSet,
			String... columnNames) throws TypeMismatchException {
		// TODO Auto-generated method stub
		return null;
	}
}
