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

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.bitdekk.aggregation.IAggregation;
import org.bitdekk.api.IBitSet;
import org.bitdekk.api.IEvaluation;
import org.bitdekk.exception.InvalidBitDekkExpressionException;
import org.bitdekk.helper.expression.model.GroupedMeasureExpression;
import org.bitdekk.model.DataRow;
import org.bitdekk.model.Table;

/**
 * Contains all the methods do do with aggregations
 * @author gaurav.saxena
 *
 */
public class AggregationHelper {
	private IEvaluation evaluator;

	public IEvaluation getEvaluator() {
		return evaluator;
	}
	public void setEvaluator(IEvaluation evaluator) {
		this.evaluator = evaluator;
	}

	public double aggregate(IAggregation aggregation, Table table, IBitSet unifiedQuery, String[] measureNames)  {
		double[] measureArray = new double[measureNames.length];
		for(DataRow i : table.getRows()) {
			if(unifiedQuery.contains(i.getMeasureQuery())) {
				for(int j = 0; j < measureNames.length; j++)
					measureArray[j] = i.getMeasureValues()[table.getMeasureIndexMap().get(measureNames[j])];
				aggregation.aggregate(measureArray);
			}
		}
		return aggregation.getValue();
	}

	public double aggregate(String tableName, IBitSet unifiedQuery, String measureExpression) throws InvalidBitDekkExpressionException{
		GroupedMeasureExpression gme = new GroupedMeasureExpression();
		Lexer lexer = evaluator.getLexer(measureExpression);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		//Parser parser = evaluator.getParser(tokens, gme);
		try {
			evaluator.parse(tokens, gme);
			return evaluator.getMeasureExpressionValue(gme.getGroupedTokens(), new Position(), null, tableName, unifiedQuery);
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new InvalidBitDekkExpressionException("Invalid token " + e.token + " found at " + e.line + "," + e.charPositionInLine);
		}
	}
}