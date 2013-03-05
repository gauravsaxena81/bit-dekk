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

public class AggregationHelper {
	private IEvaluation evaluator;

	public IEvaluation getEvaluator() {
		return evaluator;
	}
	public void setEvaluator(IEvaluation evaluator) {
		this.evaluator = evaluator;
	}

	public double aggregate(IAggregation aggregation, Table table, IBitSet viewBitSet, IBitSet filterBitSet, String[] measureNames)  {
		double[] measureArray = new double[measureNames.length];
		for(DataRow i : table.getRows()) {
			if(filterBitSet.contains(i.getMeasureQuery()) && i.getMeasureQuery().contains(viewBitSet)) {
				for(int j = 0; j < measureNames.length; j++)
					measureArray[j] = i.getMeasureValues()[table.getMeasureIndexMap().get(measureNames[j])];
				aggregation.aggregate(measureArray);
			}
		}
		return aggregation.getValue();
	}

	public double aggregate(String tableName, IBitSet viewBitSet, IBitSet filterBitSet, String measureExpression) throws InvalidBitDekkExpressionException{
		GroupedMeasureExpression gme = new GroupedMeasureExpression();
		Lexer lexer = evaluator.getLexer(measureExpression);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		//Parser parser = evaluator.getParser(tokens, gme);
		try {
			evaluator.parse(tokens, gme);
			return evaluator.getMeasureExpressionValue(gme.getGroupedTokens(), 0, null, tableName, viewBitSet, filterBitSet);
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new InvalidBitDekkExpressionException("Invalid token " + e.token + " found at " + e.line + "," + e.charPositionInLine);
		}
	}
}